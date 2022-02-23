# Get the base image from NCI. CentOS7 with Tomcat 8.5
#FROM ncidockerhub.nci.nih.gov/cbiit/centos7_tomcat85

# Get the base image from a standard tomcat8.5 with Java 8 image
FROM  tomcat:8.5.75-jre8-temurin

ENV JAVA_TOOL_OPTIONS -Dfile.encoding=UTF8

## This is for centos7_tomcat85
#USER root
#ADD --chown=root rewrite.config /local/content/tomcat/container/work/Catalina/localhost
#ADD --chown=tomcata server_nci.xml /local/content/tomcat/container/conf/server.xml
#ADD --chown=tomcata ./artifacts/evscompare.war /local/content/tomcat/container/webapps/

## This is for normal tomcat deploy
ADD rewrite.config /usr/local/tomcat/conf/Catalina/localhost/
ADD server_tomcat.xml /usr/local/tomcat/conf/server.xml
ADD ./artifacts/evscompare.war /usr/local/tomcat/webapps

#ADD --chown=tomcata run.sh /
#RUN chmod 755 /run.sh
