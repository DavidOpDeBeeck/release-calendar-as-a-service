package be.davidopdebeeck.rcaasapi.transferobject.project.release;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import static java.util.Objects.requireNonNull;

@JsonDeserialize(builder = ReleaseSpecificationTO.Builder.class)
public class ReleaseSpecificationTO {

    private final SprintBasedReleaseSpecificationTO sprintBased;

    private ReleaseSpecificationTO(Builder builder) {
        sprintBased = requireNonNull(builder.sprintBased);
    }

    public SprintBasedReleaseSpecificationTO getSprintBased() {
        return sprintBased;
    }

    @JsonPOJOBuilder
    public static final class Builder {

        private SprintBasedReleaseSpecificationTO sprintBased;

        public Builder withSprintBased(SprintBasedReleaseSpecificationTO sprintBased) {
            this.sprintBased = sprintBased;
            return this;
        }

        public ReleaseSpecificationTO build() {
            return new ReleaseSpecificationTO(this);
        }
    }
}
