FROM amazoncorretto:17-alpine-jdk

WORKDIR /app

COPY . .

RUN ./gradlew bootJar --no-daemon

FROM amazoncorretto:17-alpine-jdk

COPY --from=0 app/application/build/libs/application-0.0.1-SNAPSHOT.jar app.jar

RUN unzip app.jar

ENTRYPOINT ["java", "-XX:CICompilerCount=2", "-XX:MetaspaceSize=50m", "org.springframework.boot.loader.JarLauncher"]