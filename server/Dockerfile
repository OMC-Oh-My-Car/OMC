FROM openjdk:17-jdk-slim-buster
EXPOSE 8080
ARG JAR_FILE=build/libs/omc-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
