FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install openjdk-17-jdk -y


FROM maven:3.8.7-eclipse-temurin-19 as build
WORKDIR /app
COPY pom.xml .
COPY src/ src/
RUN mvn -f pom.xml clean package

FROM eclipse-temurin:19
WORKDIR /app
COPY --from=build /app/target/*.jar /app/flightaware.jar
ENTRYPOINT [“java”,“-jar”,“/flightaware.jar”]