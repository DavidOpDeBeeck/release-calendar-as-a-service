dependencies {
    implementation(project(":framework"))
    implementation(project(":core:domain"))
    implementation(project(":core:driving-port"))
    implementation(project(":core:driven-port"))

    testImplementation(project(":core:test-constant"))
    testImplementation("app.dodb:smd-spring-boot-starter-test")
}