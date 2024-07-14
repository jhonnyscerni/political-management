FROM maven:3.8.1-openjdk-11-slim as build

WORKDIR /app

COPY ../../pom.xml .
RUN mvn dependency:go-offline

COPY .. ./src

RUN mvn package

FROM openjdk:11-jre-slim

COPY --from=build /app/target/*-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]