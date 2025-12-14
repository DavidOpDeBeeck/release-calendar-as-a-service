package be.davidopdebeeck.rcaasapi.application.observability;

import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.SpanProcessor;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import io.opentelemetry.sdk.trace.export.SpanExporter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static io.opentelemetry.semconv.ServiceAttributes.SERVICE_NAME;

@Configuration
public class OpenTelemetryConfig {

    private final String traceEndpoint;
    private final String serviceName;

    public OpenTelemetryConfig(@Value("${otel.trace.endpoint}") String traceEndpoint,
                               @Value("${spring.application.name}") String serviceName) {
        this.traceEndpoint = traceEndpoint;
        this.serviceName = serviceName;
    }

    @Bean
    public SpanExporter spanExporter() {
        return OtlpHttpSpanExporter.builder()
            .setEndpoint(traceEndpoint)
            .build();
    }

    @Bean
    public SpanProcessor spanProcessor(SpanExporter spanExporter) {
        return BatchSpanProcessor.builder(spanExporter).build();
    }

    @Bean
    public SdkTracerProvider sdkTracerProvider(SpanProcessor spanProcessor) {
        Resource resource = Resource.create(Attributes.of(SERVICE_NAME, serviceName));
        return SdkTracerProvider.builder()
            .setResource(resource)
            .addSpanProcessor(spanProcessor)
            .build();
    }

    @Bean
    public OpenTelemetrySdk openTelemetrySdk(SdkTracerProvider sdkTracerProvider) {
        return OpenTelemetrySdk.builder()
            .setTracerProvider(sdkTracerProvider)
            .build();
    }
}