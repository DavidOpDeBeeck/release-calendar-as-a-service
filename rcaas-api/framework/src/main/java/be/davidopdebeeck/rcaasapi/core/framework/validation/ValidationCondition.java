package be.davidopdebeeck.rcaasapi.core.framework.validation;

import static java.util.Objects.requireNonNull;

public class ValidationCondition {

    public static ValidationCondition.Builder validateNonNull(Object obj) {
        return new Builder(obj != null);
    }

    public static ValidationCondition.Builder validateTrue(boolean condition) {
        return new Builder(condition);
    }

    private final boolean satisfied;
    private final ValidationMessage message;

    private ValidationCondition(Builder builder) {
        satisfied = requireNonNull(builder.satisfied);
        message = requireNonNull(builder.message);
    }

    public boolean isSatisfied() {
        return satisfied;
    }

    public ValidationMessage getMessage() {
        return message;
    }

    public static final class Builder {

        private final Boolean satisfied;
        private ValidationMessage message;

        public Builder(boolean satisfied) {
            this.satisfied = satisfied;
        }

        public ValidationCondition message(ValidationMessage message) {
            this.message = message;
            return new ValidationCondition(this);
        }
    }
}
