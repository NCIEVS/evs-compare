#!/bin/bash

ROOT_DIR=$(pwd)

# Example call:
# ./buildMain.sh dev https://github.com/NCIEVS/evs-compare.git dev <username> <pw>

#*****************************************************************
# Docker image tags to be created for lexevs artifacts
# These images will be on the NCI docker hub
#*****************************************************************
TAG_EVS_COMPARE_BUILDER=evs-compare-builder:DEV
TAG_EVS_COMPARE=evs-compare:DEV

EVS_COMPARE_BRANCH=${1:-dev}
EVS_COMPARE_REPO=${2:-https://github.com/NCIEVS/evs-compare.git}

#For the maven build, is this building dev, qa, stage, or prod.
EVS_COMPARE_BUILD_LEVEL=${3:-dev}

NCI_DOCKER_USER=${4}
NCI_DOCKER_PW=${5}

#*****************************************************************
# Output the user variables
#*****************************************************************
echo
echo "User Input:"
echo
echo EVS_COMPARE_BRANCH      : $EVS_COMPARE_BRANCH
echo EVS_COMPARE_REPO        : $EVS_COMPARE_REPO
echo
echo EVS_COMPARE_BUILD_LEVEL : $EVS_COMPARE_BUILD_LEVEL
echo
echo NCI_DOCKER_USER         : $NCI_DOCKER_USER
echo

#*****************************************************************
# Artifacts that are built and tested to exist
#*****************************************************************
EVSCOMPARE_ARTIFACT="$ROOT_DIR/build/artifacts/evscompare.war"

#*****************************************************************
# create maven container for sharing the cache files
#*****************************************************************
MAVEN_CONTAINER=$(docker run -d -P --name maven -v ~/.m2:/root/.m2:rw -v ~/.ivy2:/root/.ivy2:rw ubuntu)

#*****************************************************************
# Log into NCI Dockerhub
#*****************************************************************
function loginToNCIDockerHub() {
	# Verify that the NCI Nexus password is passed in.
	# Exit if it is not set.
	if [ -z "$NCI_DOCKER_PW" ];
		then echo "NCI_DOCKER_PW is not set. Exiting.";
		exit;
	fi

	# Log in to NCI Nexus Docker hub
	LOGIN_RESULT="$(docker login -u $NCI_DOCKER_USER -p $NCI_DOCKER_PW ncidockerhub.nci.nih.gov)"
	echo
	echo $LOGIN_RESULT
	echo

	# If the login result doesn't contain the following text, then exit.
	if [[ $LOGIN_RESULT != *"Succeeded"* ]];
		then echo "** NCI Nexus Docker login failed. Exiting. **";
		exit;
	fi
}

#*****************************************************************
# Shutdown function stop/remove any container that has been created.
#*****************************************************************
function shutdownBuild() {
	echo "*** SHUTDOWN CALLED ***"
	docker stop $MAVEN_CONTAINER
	docker rm $MAVEN_CONTAINER
}

#*****************************************************************
# Remove artifact directories and recreate them fresh.
#*****************************************************************
function cleanWorkspace() {
	rm -rf $ROOT_DIR/build
	rm -rf $ROOT_DIR/evscompare/artifacts
	mkdir $ROOT_DIR/build
	mkdir $ROOT_DIR/build/artifacts
	mkdir $ROOT_DIR/build/results
	mkdir $ROOT_DIR/build/evscompare
	mkdir $ROOT_DIR/evscompare/artifacts
}

#*****************************************************************
# evs-compare builder
#*****************************************************************
function buildArtifacts() {
	cd evscompare-builder
	docker build --tag $TAG_EVS_COMPARE_BUILDER .
	docker push $TAG_ARTIFACT_BUILDER
	docker run --rm -v $ROOT_DIR/build/results:/results -v $ROOT_DIR/build/evscompare:/evscompare -v $ROOT_DIR/build/artifacts:/artifacts -v $ROOT_DIR/evscompare/artifacts:/evscompare-local-artifacts -e EVS_COMPARE_BRANCH=$EVS_COMPARE_BRANCH -e EVS_COMPARE_REPO=$EVS_COMPARE_REPO -e EVS_COMPARE_BUILD_LEVEL=$EVS_COMPARE_BUILD_LEVEL --volumes-from maven $TAG_EVS_COMPARE_BUILDER
	echo "EVS-Compare builder completed";
	cd ..

	#*****************************************************************
	# Verify evs-compare was built successfully.
	# If it wasn't, then exit.
	#*****************************************************************
	if [[ ! -e "$EVSCOMPARE_ARTIFACT" ]];
	then
		echo "EVS-COMPARE BUILD FAILED "
		shutdownBuild
		exit 1
	else
		echo "EVS-COMPARE BUILD SUCCESSFUL"
	fi
}

#*****************************************************************
# Deploy evs-compare
#*****************************************************************
function deployArtifacts() {
	cd evscompare
	docker build -f $ROOT_DIR/evscompare/Dockerfile --tag $TAG_EVS_COMPARE .
	docker push $TAG_EVS_COMPARE
	EVS_COMPARE_CONTAINER=$(docker run -d --name evs-comare -p 8081:8080  $TAG_EVS_COMPARE)

	echo "DONE - BUILDING EVS-COMPARE CONTAINER"
	cd ..
}

# Log into NCI Dockerhub, if we need to get/push an image to there.
#loginToNCIDockerHub

# Remove old Artifacts
cleanWorkspace

# This requires you to be off the VPN in order to pull down NPM plugins
buildArtifacts

# This requires you to be ON VPN IF pulling ncidockerhub.nci.nih.gov/cbiit/centos7_tomcat85
deployArtifacts

#*****************************************************************
# Build is done. Time to shutdown/remove containers
#*****************************************************************
shutdownBuild
