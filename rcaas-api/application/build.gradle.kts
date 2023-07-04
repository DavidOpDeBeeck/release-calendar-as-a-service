plugins {
    id("org.springframework.boot") version "3.0.6"
}

dependencies {
    runtimeOnly("org.postgresql:postgresql")

    implementation(project(":driving-adapter"))
    implementation(project(":core:use-case"))
    implementation(project(":driven-adapter"))

    testImplementation(project(":core:test-constant"))
    testImplementation(project(":core:transfer-object"))
    testImplementation("org.springframework.boot:spring-boot-starter-web")
}