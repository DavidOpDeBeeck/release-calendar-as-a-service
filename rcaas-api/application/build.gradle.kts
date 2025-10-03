plugins {
    id("org.springframework.boot") version "3.5.6"
}

dependencies {
    runtimeOnly("org.postgresql:postgresql")
    runtimeOnly("io.micrometer:micrometer-registry-prometheus")

    implementation(project(":driving-adapter"))
    implementation(project(":core:use-case"))
    implementation(project(":driven-adapter"))
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("app.dodb:smd-spring-boot-starter")

    testImplementation(project(":core:test-constant"))
    testImplementation(project(":core:transfer-object"))
    testImplementation("org.testcontainers:postgresql")
    testImplementation("org.testcontainers:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-webflux")
}