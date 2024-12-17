plugins {
    id("org.springframework.boot") version "3.1.5"
}

dependencies {
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")

    implementation(project(":driving-adapter"))
    implementation(project(":core:use-case"))
    implementation(project(":driven-adapter"))
    implementation("com.github.loki4j:loki-logback-appender")
    implementation("org.springframework.boot:spring-boot-starter-actuator")

    testImplementation(project(":core:test-constant"))
    testImplementation(project(":core:transfer-object"))
    testImplementation("org.testcontainers:postgresql")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-web")
}