package be.davidopdebeeck.rcaasapi.core.domain.project.environment;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Environment {

    public static Environment environment(String value) {
        return new Environment(value);
    }

    private final String value;

    private Environment(String value) {
        this.value = requireNonNull(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Environment that = (Environment) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
