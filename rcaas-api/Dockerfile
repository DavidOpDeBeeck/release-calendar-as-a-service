FROM amazoncorretto:25-alpine AS build

WORKDIR /workspace

COPY . .
RUN chmod +x gradlew && ./gradlew :application:bootJar --no-daemon

FROM amazoncorretto:25-alpine

RUN apk add --no-cache curl \
    && addgroup -S rcaas \
    && adduser -S -G rcaas rcaas

WORKDIR /app

COPY --from=build --chown=rcaas:rcaas /workspace/application/build/libs/*.jar app.jar

EXPOSE 8080 8081

USER rcaas

ENTRYPOINT ["java", "-jar", "app.jar"]
