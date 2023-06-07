package be.davidopdebeeck.rcaasapi.core.domain.project.calendar;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class Calendar {

    private final List<Week> weeks;

    private Calendar(Builder builder) {
        weeks = requireNonNull(builder.weeks);
    }

    public List<Week> getWeeks() {
        return weeks;
    }

    public static final class Builder {

        private List<Week> weeks;

        public Builder withWeeks(List<Week> weeks) {
            this.weeks = weeks;
            return this;
        }

        public Calendar build() {
            return new Calendar(this);
        }
    }
}
