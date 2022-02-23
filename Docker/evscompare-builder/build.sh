#!/bin/bash

BUILDDIR='/build'
mkdir $BUILDDIR && cd $BUILDDIR

export MAVEN_OPTS="-Dhttps.protocols=TLSv1.1,TLSv1.2 -Dforce.http.jre.executor=true -Xmx3072m"

echo "-----------------------------------"
echo "branch      = " $EVS_COMPARE_BRANCH
echo "repo        = " $EVS_COMPARE_REPO
echo "build level = " $EVS_COMPARE_BUILD_LEVEL
echo "-----------------------------------"

# EVS Compare - get from git and build
git clone --branch $EVS_COMPARE_BRANCH $EVS_COMPARE_REPO && \
    cd evs-compare && \
    mvn clean install -DBuild_Env=build-$EVS_COMPARE_BUILD_LEVEL

echo "done building evs-compare";

# copy the war file to the shared volume
cp -r /$BUILDDIR/evs-compare/searchservice/target/*.war /artifacts/evscompare.war
cp -r /$BUILDDIR/evs-compare/searchservice/target/*.war /evscompare-local-artifacts/evscompare.war

chmod -R 775 /artifacts
chmod -R 775 /evscompare-local-artifacts
