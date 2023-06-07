package be.davidopdebeeck.rcaasapi.transferobject.project.environment;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import static java.util.Objects.requireNonNull;

public class EnvironmentTO {

    @JsonCreator
    public static EnvironmentTO environmentTO(String value) {
        return new EnvironmentTO(value);
    }

    private final String value;

    private EnvironmentTO(String value) {
        this.value = requireNonNull(value);
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}
