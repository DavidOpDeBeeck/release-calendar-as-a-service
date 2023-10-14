package be.davidopdebeeck.rcaasapi.transferobject.project.calendar;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

import static java.util.Objects.requireNonNull;

@JsonDeserialize(builder = WeekTO.Builder.class)
public class WeekTO {

    private final List<DayTO> days;

    private WeekTO(Builder builder) {
        days = requireNonNull(builder.days);
    }

    public List<DayTO> getDays() {
        return days;
    }

    @JsonPOJOBuilder
    public static final class Builder {

        private List<DayTO> days;

        public Builder withDays(List<DayTO> days) {
            this.days = days;
            return this;
        }

        public WeekTO build() {
            return new WeekTO(this);
        }
    }
}
