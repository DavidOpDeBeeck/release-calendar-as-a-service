package be.davidopdebeeck.rcaasapi.transferobject.project.release;

import be.davidopdebeeck.rcaasapi.transferobject.project.version.SprintBasedVersionTO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

@JsonDeserialize(builder = SprintBasedReleaseSpecificationTO.Builder.class)
public class SprintBasedReleaseSpecificationTO {

    private final SprintBasedVersionTO version;
    private final LocalDate startDate;
    private final int sprintLength;
    private final List<ReschedulingTO> reschedulings;

    private SprintBasedReleaseSpecificationTO(Builder builder) {
        version = requireNonNull(builder.version);
        startDate = requireNonNull(builder.startDate);
        sprintLength = requireNonNull(builder.sprintLength);
        reschedulings = requireNonNull(builder.reschedulings);
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

    public List<ReschedulingTO> getReschedulings() {
        return reschedulings;
    }

    @JsonPOJOBuilder
    public static final class Builder {

        private SprintBasedVersionTO version;
        private LocalDate startDate;
        private Integer sprintLength;
        private List<ReschedulingTO> reschedulings = new ArrayList<>();

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

        public Builder withReschedulings(List<ReschedulingTO> reschedulings) {
            this.reschedulings = reschedulings;
            return this;
        }

        public SprintBasedReleaseSpecificationTO build() {
            return new SprintBasedReleaseSpecificationTO(this);
        }
    }
}
