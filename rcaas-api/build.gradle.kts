plugins {
    `java-library`
}

subprojects {
    apply(plugin = "java-library")

    group = "be.davidopdebeeck"
    version = "0.0.1-SNAPSHOT"
    java.sourceCompatibility = JavaVersion.VERSION_17

    repositories {
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        implementation(platform("org.springframework.boot:spring-boot-dependencies:3.1.0"))

        implementation("org.apache.commons:commons-lang3")
        implementation("org.springframework.boot:spring-boot-autoconfigure")

        testImplementation("io.projectreactor:reactor-test")
        testImplementation("org.springframework.boot:spring-boot-starter-test")

        constraints {
            implementation("org.apache.commons:commons-lang3:3.12.0")
            implementation("com.fasterxml.jackson.core:jackson-databind:2.15.1")
            implementation("com.fasterxml.jackson.core:jackson-annotations:2.15.1")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
