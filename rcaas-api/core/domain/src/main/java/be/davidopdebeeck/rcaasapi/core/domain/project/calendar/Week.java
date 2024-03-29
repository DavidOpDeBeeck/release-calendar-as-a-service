package be.davidopdebeeck.rcaasapi.core.domain.project.calendar;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class Week {

    private final List<Day> days;

    private Week(Builder builder) {
        days = requireNonNull(builder.days);
    }

    public List<Day> getDays() {
        return days;
    }

    public static final class Builder {

        private List<Day> days;

        public Builder withDays(List<Day> days) {
            this.days = days;
            return this;
        }

        public Week build() {
            return new Week(this);
        }
    }
}
