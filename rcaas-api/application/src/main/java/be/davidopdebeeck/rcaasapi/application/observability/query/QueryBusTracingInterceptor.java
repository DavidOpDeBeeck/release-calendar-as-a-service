package be.davidopdebeeck.rcaasapi.application.observability.query;

import app.dodb.smd.api.query.Query;
import app.dodb.smd.api.query.QueryMessage;
import app.dodb.smd.api.query.bus.QueryBusInterceptor;
import app.dodb.smd.api.query.bus.QueryBusInterceptorChain;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static app.dodb.smd.api.utils.ExceptionUtils.rethrow;

@Order(1)
@Component
public class QueryBusTracingInterceptor implements QueryBusInterceptor {

    private final Tracer tracer;

    public QueryBusTracingInterceptor(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public <R, Q extends Query<R>> R intercept(QueryMessage<R, Q> queryMessage, QueryBusInterceptorChain<R, Q> chain) {
        Span span = tracer.nextSpan()
            .name("query.execution")
            .tag("query", queryMessage.payload().getClass().getSimpleName());

        try (Tracer.SpanInScope scope = tracer.withSpan(span.start())) {
            R result = chain.proceed(queryMessage);
            span.tag("outcome", "success");
            return result;
        } catch (Exception ex) {
            span.tag("outcome", "failure");
            span.error(ex);
            throw rethrow(ex);
        } finally {
            span.end();
        }
    }
}
