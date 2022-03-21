# FROM maven:3.6.3-jdk-8-slim AS build
# COPY ../* /home/app/src
# COPY pom.xml /home/app
# RUN mvn -f /home/app/pom.xml clean package

FROM adoptopenjdk:11-jre-hotspot
#ARG JAR_FILE=./core/target/**.jar
COPY ./core/target/*.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
