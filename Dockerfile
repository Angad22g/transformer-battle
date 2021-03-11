# For Java 11, try this
FROM openjdk:11-jre-slim

# Refer to Maven build -> finalName
ARG JAR_FILE=target/transformers-battle-api-1.0.0.jar

WORKDIR /opt/app

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","app.jar"]