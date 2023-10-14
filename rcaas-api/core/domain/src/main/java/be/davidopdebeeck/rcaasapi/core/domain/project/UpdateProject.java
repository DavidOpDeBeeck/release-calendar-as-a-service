package be.davidopdebeeck.rcaasapi.core.domain.project;

import be.davidopdebeeck.rcaasapi.core.domain.project.release.ReleaseSpecification;

import java.util.List;

import static be.davidopdebeeck.rcaasapi.core.domain.project.ProjectValidationMessages.PROJECT_NAME_SHOULD_NOT_BE_NULL;
import static be.davidopdebeeck.rcaasapi.core.framework.validation.ValidationCondition.validateNonNull;
import static be.davidopdebeeck.rcaasapi.core.framework.validation.ValidationConstraints.validateConstraints;
import static java.util.Objects.requireNonNull;

public class UpdateProject {

    private final String name;
    private final List<ReleaseSpecification> specifications;

    private UpdateProject(Builder builder) {
        name = requireNonNull(builder.name);
        specifications = requireNonNull(builder.specifications);
    }

    public String getName() {
        return name;
    }

    public List<ReleaseSpecification> getSpecifications() {
        return specifications;
    }

    public static final class Builder {

        private String name;
        private List<ReleaseSpecification> specifications;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSpecifications(List<ReleaseSpecification> specifications) {
            this.specifications = specifications;
            return this;
        }

        public UpdateProject build() {
            validateConstraints(
                validateNonNull(name).message(PROJECT_NAME_SHOULD_NOT_BE_NULL)
            );
            return new UpdateProject(this);
        }
    }
}
