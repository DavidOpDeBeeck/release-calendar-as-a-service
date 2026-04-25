package be.davidopdebeeck.rcaasapi.application.observability;

import io.micrometer.tracing.Tracer;
import io.micrometer.tracing.otel.bridge.OtelBaggageManager;
import io.micrometer.tracing.otel.bridge.OtelCurrentTraceContext;
import io.micrometer.tracing.otel.bridge.OtelTracer;
import io.opentelemetry.api.OpenTelemetry;
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
import static java.util.Collections.emptyList;

@Configuration
public class OpenTelemetryConfig {

    @Bean
    public OpenTelemetry openTelemetry(
        @Value("${otel.trace.endpoint}") String endpoint,
        @Value("${spring.application.name}") String serviceName) {

        var resource = Resource.create(Attributes.of(SERVICE_NAME, serviceName));

        var sdkTracerProvider = SdkTracerProvider.builder()
            .setResource(resource)
            .addSpanProcessor(BatchSpanProcessor.builder(
                OtlpHttpSpanExporter.builder()
                    .setEndpoint(endpoint)
                    .build()
            ).build())
            .build();

        return OpenTelemetrySdk.builder()
            .setTracerProvider(sdkTracerProvider)
            .build();
    }

    @Bean
    public Tracer tracer(OpenTelemetry openTelemetry, @Value("${spring.application.name}") String serviceName) {
        var traceContext = new OtelCurrentTraceContext();
        var otelTracer = openTelemetry.getTracer(serviceName);

        return new OtelTracer(
            otelTracer,
            traceContext,
            event -> {},
            new OtelBaggageManager(traceContext, emptyList(), emptyList())
        );
    }
}