plugins {
    `java-library`
}

subprojects {
    apply(plugin = "java-library")

    group = "be.davidopdebeeck"
    version = "1.0.0"
    java.sourceCompatibility = JavaVersion.VERSION_25

    repositories {
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        implementation(platform(rootProject.libs.spring.boot.dependencies))

        implementation(rootProject.libs.smd.api)
        implementation(rootProject.libs.commons.lang3)
        implementation(rootProject.libs.spring.boot.autoconfigure)

        testImplementation(rootProject.libs.reactor.test)
        testImplementation(rootProject.libs.spring.boot.starter.test)
        testRuntimeOnly(rootProject.libs.junit.platform.launcher)

        constraints {
            implementation(rootProject.libs.smd.spring.boot.starter)
            implementation(rootProject.libs.jackson.databind)
            implementation(rootProject.libs.jackson.annotations)
            testImplementation(rootProject.libs.smd.spring.boot.starter.test)
            testImplementation(rootProject.libs.testcontainers.postgresql)
            testImplementation(rootProject.libs.testcontainers.junit.jupiter)
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
