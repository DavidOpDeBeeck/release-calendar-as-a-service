plugins {
    `java-library`
}

subprojects {
    apply(plugin = "java-library")

    group = "be.davidopdebeeck"
    version = "1.0.0"
    java.sourceCompatibility = JavaVersion.VERSION_21

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
            implementation("app.dodb:smd-api:0.0.1")
            implementation("app.dodb:smd-spring-boot-starter:0.0.1")
            implementation("org.springframework.boot:spring-boot-dependencies:3.5.0")
            implementation("org.yaml:snakeyaml:2.2")
            implementation("org.apache.commons:commons-lang3:3.13.0")
            implementation("com.fasterxml.jackson.core:jackson-databind:2.15.3")
            implementation("com.fasterxml.jackson.core:jackson-annotations:2.15.3")
            testImplementation("app.dodb:smd-spring-boot-starter-test:0.0.1")
            testImplementation("org.testcontainers:postgresql:1.18.3")
            testImplementation("org.testcontainers:junit-jupiter:1.18.3")
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}
