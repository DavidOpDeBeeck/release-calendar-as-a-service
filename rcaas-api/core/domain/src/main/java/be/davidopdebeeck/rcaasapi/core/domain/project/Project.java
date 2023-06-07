package be.davidopdebeeck.rcaasapi.core.domain.project;

import be.davidopdebeeck.rcaasapi.core.domain.project.calendar.Calendar;
import be.davidopdebeeck.rcaasapi.core.domain.project.calendar.CalendarFactory;
import be.davidopdebeeck.rcaasapi.core.domain.project.release.ReleaseSpecification;

import java.time.YearMonth;
import java.util.List;

import static be.davidopdebeeck.rcaasapi.core.domain.project.ProjectValidationMessages.PROJECT_NAME_SHOULD_NOT_BE_NULL;
import static be.davidopdebeeck.rcaasapi.core.framework.validation.ValidationCondition.validateNonNull;
import static be.davidopdebeeck.rcaasapi.core.framework.validation.ValidationConstraints.validateConstraints;
import static java.util.Objects.requireNonNull;

public class Project {

    private final ProjectId projectId;
    private String name;
    private List<ReleaseSpecification> specifications;

    private Project(Builder builder) {
        projectId = requireNonNull(builder.projectId);
        name = requireNonNull(builder.name);
        specifications = requireNonNull(builder.specifications);
    }

    public ProjectId getProjectId() {
        return projectId;
    }

    public String getName() {
        return name;
    }

    public List<ReleaseSpecification> getSpecifications() {
        return specifications;
    }

    public Calendar createCalendarFor(YearMonth yearMonth) {
        return new CalendarFactory(this)
            .create(yearMonth);
    }

    public void update(UpdateProject updateProject) {
        this.name = updateProject.getName();
        this.specifications = updateProject.getSpecifications();
    }

    public static final class Builder {

        private ProjectId projectId;
        private String name;
        private List<ReleaseSpecification> specifications;

        public Builder withProjectId(ProjectId projectId) {
            this.projectId = projectId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withSpecifications(List<ReleaseSpecification> specifications) {
            this.specifications = specifications;
            return this;
        }

        public Project build() {
            validateConstraints(
                validateNonNull(name).message(PROJECT_NAME_SHOULD_NOT_BE_NULL)
            );
            return new Project(this);
        }
    }
}
