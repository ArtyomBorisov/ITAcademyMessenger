FROM tomcat:8.5-jre11-temurin
COPY target/Messenger.war /usr/local/tomcat/webapps
CMD ["catalina.sh", "run"]