dependencies {
    implementation(project(":framework"))
    implementation(project(":core:domain"))
    implementation(project(":core:driving-port"))
    implementation(project(":core:driven-port"))
    implementation(libs.smd.spring.boot.starter)

    testImplementation(project(":core:test-constant"))
    testImplementation(libs.smd.spring.boot.starter.test)
}
