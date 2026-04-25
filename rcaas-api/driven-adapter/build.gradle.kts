dependencies {
    implementation(project(":core:driven-port"))

    implementation(libs.spring.boot.starter.liquibase)
    implementation(libs.spring.boot.starter.web)
    implementation(libs.spring.boot.starter.data.jpa)
    implementation(libs.jackson.databind)
    implementation(libs.jackson.datatype.jdk8)
    implementation(libs.jackson.datatype.jsr310)

    testRuntimeOnly(libs.postgresql)
    testImplementation(project(":core:test-constant"))
    testImplementation(libs.testcontainers.postgresql)
    testImplementation(libs.testcontainers.junit.jupiter)
}
