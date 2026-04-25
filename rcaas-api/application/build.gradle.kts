plugins {
    alias(libs.plugins.spring.boot)
}

dependencies {
    runtimeOnly(libs.postgresql)
    runtimeOnly(libs.micrometer.registry.prometheus)

    implementation(project(":driving-adapter"))
    implementation(project(":core:use-case"))
    implementation(project(":driven-adapter"))
    implementation(libs.spring.boot.starter.actuator)
    implementation(libs.smd.spring.boot.starter)
    implementation(libs.opentelemetry.api)
    implementation(libs.opentelemetry.sdk)
    implementation(libs.opentelemetry.exporter.otlp)
    implementation(libs.micrometer.tracing.bridge.otel)

    testImplementation(project(":core:test-constant"))
    testImplementation(project(":core:transfer-object"))
    testImplementation(libs.testcontainers.postgresql)
    testImplementation(libs.testcontainers.junit.jupiter)
    testImplementation(libs.spring.boot.starter.webflux)
}
