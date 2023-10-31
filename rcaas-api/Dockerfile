FROM amazoncorretto:21-alpine-jdk

WORKDIR /app

COPY . .

RUN ./gradlew bootJar --no-daemon

FROM amazoncorretto:21-alpine-jdk

COPY --from=0 app/application/build/libs/application-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]