dependencies {
    implementation(project(":core:driven-port"))

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    testRuntimeOnly("org.postgresql:postgresql")
    testImplementation(project(":core:test-constant"))
    testImplementation("org.testcontainers:postgresql")
    testImplementation("org.testcontainers:junit-jupiter")
}