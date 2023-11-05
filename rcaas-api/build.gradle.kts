plugins {
    `java-library`
}

subprojects {
    apply(plugin = "java-library")

    group = "be.davidopdebeeck"
    version = "0.0.1-SNAPSHOT"
    java.sourceCompatibility = JavaVersion.VERSION_21

    repositories {
        mavenLocal()
        mavenCentral()
    }

    dependencies {
        implementation(platform("org.springframework.boot:spring-boot-dependencies:3.1.5"))

        implementation("org.apache.commons:commons-lang3")
        implementation("org.springframework.boot:spring-boot-autoconfigure")

        testImplementation("io.projectreactor:reactor-test")
        testImplementation("org.springframework.boot:spring-boot-starter-test")

        constraints {
            implementation("org.yaml:snakeyaml:2.2")
            implementation("org.apache.commons:commons-lang3:3.13.0")
            implementation("com.fasterxml.jackson.core:jackson-databind:2.15.3")
            implementation("com.fasterxml.jackson.core:jackson-annotations:2.15.3")
            testImplementation("org.testcontainers:postgresql:1.18.3")
            testImplementation("org.testcontainers:junit-jupiter:1.18.3")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
