package be.davidopdebeeck.rcaasapi.core.domain.project;

import static java.util.Objects.requireNonNull;

public class ProjectId {

    public static ProjectId projectId(String value) {
        return new ProjectId(value);
    }

    private final String value;

    private ProjectId(String value) {
        this.value = requireNonNull(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectId that = (ProjectId) o;

        return value.equals(that.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
