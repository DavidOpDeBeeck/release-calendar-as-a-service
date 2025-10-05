package be.davidopdebeeck.rcaasapi.application.metrics;

import app.dodb.smd.api.command.Command;
import app.dodb.smd.api.command.CommandMessage;
import app.dodb.smd.api.command.bus.CommandBusInterceptor;
import app.dodb.smd.api.command.bus.CommandBusInterceptorChain;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.stereotype.Component;

import static app.dodb.smd.api.utils.ExceptionUtils.rethrow;

@Component
public class CommandBusMetricsInterceptor implements CommandBusInterceptor {

    private final MeterRegistry meterRegistry;

    public CommandBusMetricsInterceptor(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public <R, C extends Command<R>> R intercept(CommandMessage<R, C> commandMessage, CommandBusInterceptorChain<R, C> chain) {
        Timer.Sample sample = Timer.start(meterRegistry);
        String commandName = commandMessage.payload().getClass().getSimpleName();
        try {
            R result = chain.proceed(commandMessage);
            sample.stop(Timer.builder("command.execution.time")
                .description("Time taken to execute a command")
                .tag("command", commandName)
                .tag("outcome", "success")
                .register(meterRegistry));
            return result;
        } catch (Exception exception) {
            sample.stop(Timer.builder("command.execution.time")
                .description("Time taken to execute a command")
                .tag("command", commandName)
                .tag("outcome", "failure")
                .register(meterRegistry));
            throw rethrow(exception);
        }
    }
}
