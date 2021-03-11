# For Java 11, try this
FROM openjdk:8-alpine

# Refer to Maven build -> finalName
ARG JAR_FILE=target/transformers-battle-api-1.0.0.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]