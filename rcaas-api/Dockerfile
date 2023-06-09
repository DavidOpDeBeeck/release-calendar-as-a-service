FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY . .

RUN ./gradlew clean build -x test

FROM openjdk:17-jdk-alpine

USER 0

COPY ./application.jar app.jar

USER 0

ENTRYPOINT ["java", "-jar", "/app.jar"]