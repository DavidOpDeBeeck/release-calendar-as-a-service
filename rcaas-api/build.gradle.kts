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
        implementation(platform("org.springframework.boot:spring-boot-dependencies"))

        implementation("app.dodb:smd-api")
        implementation("org.apache.commons:commons-lang3")
        implementation("org.springframework.boot:spring-boot-autoconfigure")

        testImplementation("io.projectreactor:reactor-test")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")

        constraints {
            implementation("app.dodb:smd-api:0.0.3")
            implementation("app.dodb:smd-spring-boot-starter:0.0.3")
            implementation("org.springframework.boot:spring-boot-dependencies:3.5.6")
            implementation("org.apache.commons:commons-lang3:3.19.0")
            implementation("com.fasterxml.jackson.core:jackson-databind:2.20.0")
            implementation("com.fasterxml.jackson.core:jackson-annotations:2.20")
            testImplementation("app.dodb:smd-spring-boot-starter-test:0.0.3")
            testImplementation("org.testcontainers:postgresql:1.21.3")
            testImplementation("org.testcontainers:junit-jupiter:1.21.3")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
