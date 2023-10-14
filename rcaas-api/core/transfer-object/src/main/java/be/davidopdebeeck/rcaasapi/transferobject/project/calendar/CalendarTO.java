package be.davidopdebeeck.rcaasapi.transferobject.project.calendar;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;

import static java.util.Objects.requireNonNull;

@JsonDeserialize(builder = CalendarTO.Builder.class)
public class CalendarTO {

    private final List<WeekTO> weeks;

    private CalendarTO(Builder builder) {
        weeks = requireNonNull(builder.weeks);
    }

    public List<WeekTO> getWeeks() {
        return weeks;
    }

    @JsonPOJOBuilder
    public static final class Builder {

        private List<WeekTO> weeks;

        public Builder withWeeks(List<WeekTO> weeks) {
            this.weeks = weeks;
            return this;
        }

        public CalendarTO build() {
            return new CalendarTO(this);
        }
    }
}
