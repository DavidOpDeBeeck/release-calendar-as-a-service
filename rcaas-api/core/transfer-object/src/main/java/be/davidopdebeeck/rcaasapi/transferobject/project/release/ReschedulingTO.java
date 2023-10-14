package be.davidopdebeeck.rcaasapi.transferobject.project.release;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.time.LocalDate;

import static java.util.Objects.requireNonNull;

@JsonDeserialize(builder = ReschedulingTO.Builder.class)
public class ReschedulingTO {

    private final LocalDate from;
    private final LocalDate to;

    private ReschedulingTO(Builder builder) {
        from = requireNonNull(builder.from);
        to = requireNonNull(builder.to);
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    @JsonPOJOBuilder
    public static final class Builder {

        private LocalDate from;
        private LocalDate to;

        @JsonFormat(pattern = "yyyy-MM-dd")
        public Builder withFrom(LocalDate from) {
            this.from = from;
            return this;
        }

        @JsonFormat(pattern = "yyyy-MM-dd")
        public Builder withTo(LocalDate to) {
            this.to = to;
            return this;
        }

        public ReschedulingTO build() {
            return new ReschedulingTO(this);
        }
    }
}
