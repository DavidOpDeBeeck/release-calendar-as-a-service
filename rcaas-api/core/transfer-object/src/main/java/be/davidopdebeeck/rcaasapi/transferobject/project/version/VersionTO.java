package be.davidopdebeeck.rcaasapi.transferobject.project.version;

import be.davidopdebeeck.rcaasapi.transferobject.project.environment.EnvironmentTO;

import static java.util.Objects.requireNonNull;

public class VersionTO {

    private final String value;
    private final String color;
    private final EnvironmentTO environment;

    private VersionTO(Builder builder) {
        value = requireNonNull(builder.value);
        color = requireNonNull(builder.color);
        environment = requireNonNull(builder.environment);
    }

    public String getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }

    public EnvironmentTO getEnvironment() {
        return environment;
    }

    public static final class Builder {

        private String value;
        private String color;
        private EnvironmentTO environment;

        public Builder withValue(String value) {
            this.value = value;
            return this;
        }

        public Builder withColor(String color) {
            this.color = color;
            return this;
        }

        public Builder withEnvironment(EnvironmentTO environment) {
            this.environment = environment;
            return this;
        }

        public VersionTO build() {
            return new VersionTO(this);
        }
    }
}
