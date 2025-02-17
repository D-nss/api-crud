FROM openjdk:17-jdk-slim

ARG JAR_FILE=target/*.jar

COPY ./target/api-ti9-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-XX:-UseSharedSpaces", "-jar", "/app.jar"]