dependencies {
    implementation(project(":framework"))
    implementation(libs.spring.boot.autoconfigure)

    testImplementation(project(":core:test-constant"))
}
