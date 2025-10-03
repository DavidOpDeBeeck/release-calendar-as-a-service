FROM amazoncorretto:25-alpine

WORKDIR /app

COPY . .

RUN ./gradlew bootJar --no-daemon

FROM amazoncorretto:25-alpine

COPY --from=0 app/application/build/libs/application-1.0.0.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]