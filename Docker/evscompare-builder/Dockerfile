#Build EVS Compare tool
FROM ubuntu:16.04

RUN apt-get update && apt-get clean
RUN apt-get install -y curl
RUN apt-get install -y git
RUN apt-get install -y maven
RUN apt-get install -q -y openjdk-8-jre-headless openjdk-8-jdk && apt-get clean

ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/
ENV PATH $JAVA_HOME/bin:$PATH
ENV JAVA_TOOL_OPTIONS -Dfile.encoding=UTF8

ADD build.sh /
CMD bash /build.sh
