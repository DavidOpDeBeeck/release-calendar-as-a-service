package be.davidopdebeeck.rcaasapi.transferobject.project;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import static java.util.Objects.requireNonNull;

@JsonDeserialize(builder = ProjectIdTO.Builder.class)
public class ProjectIdTO {

    private final String projectId;

    private ProjectIdTO(Builder builder) {
        projectId = requireNonNull(builder.projectId);
    }

    public String getProjectId() {
        return projectId;
    }

    @JsonPOJOBuilder
    public static final class Builder {

        private String projectId;

        public Builder withProjectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public ProjectIdTO build() {
            return new ProjectIdTO(this);
        }
    }
}
