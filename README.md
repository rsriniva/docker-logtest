# docker-logtest
Logging inside Tomcat containers on a RHEL Atomic Host

On a RHEL Atomic Host running multiple Tomcat containers, a common host directory is mounted for containers to write application specific logs

Each container will create a log file of it's own like <continer_id>-applog.log

We set the hostname as a -D flag to Tomcat startup and use vars in log4j.properties to dynamically insert the hostname

Also Extended PatternLayout class of Log4j to add hostname in log entries to make it easy for parsing by ELK stack

Ex:
**valhalla** 2016-02-20 15:39:04 INFO  DispatcherServlet:484 - FrameworkServlet 'hello-dispatcher': initialization started

## Build Instructions

Copy setenv.sh to TOMCAT_HOME/bin

mvn clean package

cp target/logtest.war to Tomcat 8 webapps folder

http://localhost:8080/logtest

Enjoy!
