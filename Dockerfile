FROM amazoncorretto:11-alpine-jdk
MAINTAINER GRMG
COPY target/GRMG-0.0.1-SNAPSHOT.jar GRMG-app.jar
ENTRYPOINT ["java","-jar","/GRMG-app.jar"]
#EXPOSE 8080