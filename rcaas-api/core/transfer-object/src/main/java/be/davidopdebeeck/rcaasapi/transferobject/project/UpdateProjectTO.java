package be.davidopdebeeck.rcaasapi.transferobject.project;

import be.davidopdebeeck.rcaasapi.transferobject.project.release.ReleaseSpecificationTO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@JsonDeserialize(builder = UpdateProjectTO.Builder.class)
public class UpdateProjectTO {

    private final String name;
    private final List<ReleaseSpecificationTO> specifications;

    private UpdateProjectTO(Builder builder) {
        name = builder.name;
        specifications = requireNonNull(builder.specifications);
    }

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    public List<ReleaseSpecificationTO> getSpecifications() {
        return specifications;
    }

    @JsonPOJOBuilder
    public static final class Builder {

        private String name;
        private List<ReleaseSpecificationTO> specifications;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSpecifications(List<ReleaseSpecificationTO> specifications) {
            this.specifications = specifications;
            return this;
        }

        public UpdateProjectTO build() {
            return new UpdateProjectTO(this);
        }
    }
}
