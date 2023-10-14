package be.davidopdebeeck.rcaasapi.transferobject.project;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@JsonDeserialize(builder = CreateProjectTO.Builder.class)
public class CreateProjectTO {

    private final String name;

    private CreateProjectTO(Builder builder) {
        name = builder.name;
    }

    public Optional<String> getName() {
        return ofNullable(name);
    }

    @JsonPOJOBuilder
    public static final class Builder {

        private String name;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public CreateProjectTO build() {
            return new CreateProjectTO(this);
        }
    }
}
