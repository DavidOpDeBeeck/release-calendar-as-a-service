package be.davidopdebeeck.rcaasapi.core.domain.project.version;

import be.davidopdebeeck.rcaasapi.core.domain.project.environment.Environment;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public final class SprintBasedVersion implements Version {

    private final long value;
    private final String color;
    private final Environment environment;

    private SprintBasedVersion(Builder builder) {
        value = requireNonNull(builder.value);
        color = requireNonNull(builder.color);
        environment = requireNonNull(builder.environment);
    }

    public SprintBasedVersion increase(long value) {
        return new SprintBasedVersion.Builder()
            .withValue(this.value + value)
            .withColor(color)
            .withEnvironment(environment)
            .build();
    }

    public long getValueAsLong() {
        return value;
    }

    @Override
    public String getValue() {
        return Long.toString(value);
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SprintBasedVersion that = (SprintBasedVersion) o;
        return value == that.value && Objects.equals(environment, that.environment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, environment);
    }

    public static final class Builder {

        private Long value;
        private String color;
        private Environment environment;

        public Builder withValue(long value) {
            this.value = value;
            return this;
        }

        public Builder withColor(String color) {
            this.color = color;
            return this;
        }

        public Builder withEnvironment(Environment environment) {
            this.environment = environment;
            return this;
        }

        public SprintBasedVersion build() {
            return new SprintBasedVersion(this);
        }
    }
}
