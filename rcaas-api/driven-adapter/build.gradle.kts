dependencies {
    implementation(project(":core:driven-port"))

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    testRuntimeOnly("com.h2database:h2")
    testImplementation(project(":core:test-constant"))
}