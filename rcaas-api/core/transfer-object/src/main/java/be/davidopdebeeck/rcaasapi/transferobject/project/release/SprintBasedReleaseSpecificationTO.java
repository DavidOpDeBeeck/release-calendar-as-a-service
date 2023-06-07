package be.davidopdebeeck.rcaasapi.transferobject.project.release;

import be.davidopdebeeck.rcaasapi.transferobject.project.version.SprintBasedVersionTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDate;

import static java.util.Objects.requireNonNull;

@JsonDeserialize(builder = SprintBasedReleaseSpecificationTO.Builder.class)
public class SprintBasedReleaseSpecificationTO {

    private final SprintBasedVersionTO version;
    private final LocalDate startDate;
    private final int sprintLength;

    private SprintBasedReleaseSpecificationTO(Builder builder) {
        version = requireNonNull(builder.version);
        startDate = requireNonNull(builder.startDate);
        sprintLength = requireNonNull(builder.sprintLength);
    }

    public SprintBasedVersionTO getVersion() {
        return version;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getSprintLength() {
        return sprintLength;
    }

    @JsonPOJOBuilder
    public static final class Builder {

        private SprintBasedVersionTO version;
        private LocalDate startDate;
        private Integer sprintLength;

        public Builder withVersion(SprintBasedVersionTO version) {
            this.version = version;
            return this;
        }

        @JsonFormat(pattern = "yyyy-MM-dd")
        public Builder withStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withSprintLength(int sprintLength) {
            this.sprintLength = sprintLength;
            return this;
        }

        public SprintBasedReleaseSpecificationTO build() {
            return new SprintBasedReleaseSpecificationTO(this);
        }
    }
}
