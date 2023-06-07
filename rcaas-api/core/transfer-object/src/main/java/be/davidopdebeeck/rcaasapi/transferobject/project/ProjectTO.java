package be.davidopdebeeck.rcaasapi.transferobject.project;

import be.davidopdebeeck.rcaasapi.transferobject.project.release.ReleaseSpecificationTO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

import static java.util.Objects.requireNonNull;

@JsonDeserialize(builder = ProjectTO.Builder.class)
public class ProjectTO {

    private final String projectId;
    private final String name;
    private final List<ReleaseSpecificationTO> specifications;

    private ProjectTO(Builder builder) {
        projectId = requireNonNull(builder.projectId);
        name = requireNonNull(builder.name);
        specifications = requireNonNull(builder.specifications);
    }

    public String getProjectId() {
        return projectId;
    }

    public String getName() {
        return name;
    }

    public List<ReleaseSpecificationTO> getSpecifications() {
        return specifications;
    }

    @JsonPOJOBuilder
    public static final class Builder {

        private String projectId;
        private String name;
        private List<ReleaseSpecificationTO> specifications;

        public Builder withProjectId(String projectId) {
            this.projectId = projectId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSpecifications(List<ReleaseSpecificationTO> specifications) {
            this.specifications = specifications;
            return this;
        }

        public ProjectTO build() {
            return new ProjectTO(this);
        }
    }
}
