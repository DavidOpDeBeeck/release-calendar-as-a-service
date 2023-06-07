plugins {
    id("org.springframework.boot") version "3.0.6"
}

dependencies {
    runtimeOnly("com.h2database:h2")

    implementation(project(":driving-adapter"))
    implementation(project(":core:use-case"))
    implementation(project(":driven-adapter"))

    testImplementation(project(":core:test-constant"))
    testImplementation(project(":core:transfer-object"))
    testImplementation("org.springframework.boot:spring-boot-starter-web")
}