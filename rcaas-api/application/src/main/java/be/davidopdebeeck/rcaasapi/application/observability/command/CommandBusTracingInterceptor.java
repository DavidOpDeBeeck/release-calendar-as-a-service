package be.davidopdebeeck.rcaasapi.application.observability.command;

import app.dodb.smd.api.command.Command;
import app.dodb.smd.api.command.CommandMessage;
import app.dodb.smd.api.command.bus.CommandBusInterceptor;
import app.dodb.smd.api.command.bus.CommandBusInterceptorChain;
import io.micrometer.tracing.Span;
import io.micrometer.tracing.Tracer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import static app.dodb.smd.api.utils.ExceptionUtils.rethrow;

@Order(1)
@Component
public class CommandBusTracingInterceptor implements CommandBusInterceptor {

    private final Tracer tracer;

    public CommandBusTracingInterceptor(Tracer tracer) {
        this.tracer = tracer;
    }

    @Override
    public <R, C extends Command<R>> R intercept(CommandMessage<R, C> commandMessage, CommandBusInterceptorChain<R, C> chain) {
        Span span = tracer.nextSpan()
            .name("command.execution")
            .tag("command", commandMessage.payload().getClass().getSimpleName());

        try (Tracer.SpanInScope scope = tracer.withSpan(span.start())) {
            R result = chain.proceed(commandMessage);
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
