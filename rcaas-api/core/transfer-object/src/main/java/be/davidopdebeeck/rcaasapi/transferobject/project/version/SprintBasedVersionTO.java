package be.davidopdebeeck.rcaasapi.transferobject.project.version;

import be.davidopdebeeck.rcaasapi.transferobject.project.environment.EnvironmentTO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import static java.util.Objects.requireNonNull;

@JsonDeserialize(builder = SprintBasedVersionTO.Builder.class)
public class SprintBasedVersionTO {

    private final long value;
    private final String color;
    private final EnvironmentTO environment;

    private SprintBasedVersionTO(Builder builder) {
        value = requireNonNull(builder.value);
        color = requireNonNull(builder.color);
        environment = requireNonNull(builder.environment);
    }

    public long getValue() {
        return value;
    }

    public String getColor() {
        return color;
    }

    public EnvironmentTO getEnvironment() {
        return environment;
    }

    @JsonPOJOBuilder
    public static final class Builder {

        private Long value;
        private String color;
        private EnvironmentTO environment;

        public Builder withValue(long value) {
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

        public SprintBasedVersionTO build() {
            return new SprintBasedVersionTO(this);
        }
    }
}
