package be.davidopdebeeck.rcaasapi.application.observability.query;

import app.dodb.smd.api.query.Query;
import app.dodb.smd.api.query.QueryMessage;
import app.dodb.smd.api.query.bus.QueryBusInterceptor;
import app.dodb.smd.api.query.bus.QueryBusInterceptorChain;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static app.dodb.smd.api.utils.ExceptionUtils.rethrow;

@Order(2)
@Component
public class QueryBusMetricsInterceptor implements QueryBusInterceptor {

    private final MeterRegistry meterRegistry;

    public QueryBusMetricsInterceptor(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public <R, Q extends Query<R>> R intercept(QueryMessage<R, Q> queryMessage, QueryBusInterceptorChain<R, Q> chain) {
        Timer.Sample sample = Timer.start(meterRegistry);
        String queryName = queryMessage.payload().getClass().getSimpleName();
        try {
            R result = chain.proceed(queryMessage);
            sample.stop(Timer.builder("query.execution.time")
                .description("Time taken to execute a query")
                .tag("query", queryName)
                .tag("outcome", "success")
                .register(meterRegistry));
            return result;
        } catch (Exception exception) {
            sample.stop(Timer.builder("query.execution.time")
                .description("Time taken to execute a query")
                .tag("query", queryName)
                .tag("outcome", "failure")
                .register(meterRegistry));
            throw rethrow(exception);
        }
    }
}
