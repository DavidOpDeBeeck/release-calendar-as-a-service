FROM eclipse-temurin:17

WORKDIR /app

COPY . .

RUN ./gradlew clean build -x test

FROM eclipse-temurin:17

USER 0

COPY application/build/libs/application-0.0.1-SNAPSHOT.jar app.jar

USER 0

ENTRYPOINT ["java", "-jar", "/app.jar"]