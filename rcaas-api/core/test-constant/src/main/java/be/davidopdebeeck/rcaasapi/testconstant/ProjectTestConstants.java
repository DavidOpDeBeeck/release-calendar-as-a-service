package be.davidopdebeeck.rcaasapi.testconstant;

import be.davidopdebeeck.rcaasapi.core.domain.project.Project;
import be.davidopdebeeck.rcaasapi.core.domain.project.ProjectId;
import be.davidopdebeeck.rcaasapi.core.domain.project.environment.Environment;
import be.davidopdebeeck.rcaasapi.core.domain.project.release.SprintBasedReleaseSpecification;
import be.davidopdebeeck.rcaasapi.core.domain.project.version.SprintBasedVersion;
import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.environment.EnvironmentTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.release.ReleaseSpecificationTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.release.SprintBasedReleaseSpecificationTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.version.SprintBasedVersionTO;

import java.time.LocalDate;
import java.util.List;

import static be.davidopdebeeck.rcaasapi.core.domain.project.ProjectId.projectId;
import static be.davidopdebeeck.rcaasapi.core.domain.project.environment.Environment.environment;
import static be.davidopdebeeck.rcaasapi.transferobject.project.environment.EnvironmentTO.environmentTO;

public class ProjectTestConstants {

    public static final String PROJECT_ID_VALUE = "project-id";
    public static final ProjectId PROJECT_ID = projectId(PROJECT_ID_VALUE);
    public static final String ANOTHER_PROJECT_ID_VALUE = "another-project-id";
    public static final ProjectId ANOTHER_PROJECT_ID = projectId(ANOTHER_PROJECT_ID_VALUE);
    public static final String PROJECT_NAME = "project-name";
    public static final String ANOTHER_PROJECT_NAME = "another-project-name";
    public static final int SPRINT_VERSION_VALUE = 1;
    public static final String SPRINT_VERSION_ENVIRONMENT_VALUE = "PROD";
    public static final Environment SPRINT_VERSION_ENVIRONMENT = environment(SPRINT_VERSION_ENVIRONMENT_VALUE);
    public static final EnvironmentTO SPRINT_VERSION_ENVIRONMENT_TO = environmentTO(SPRINT_VERSION_ENVIRONMENT_VALUE);
    public static final String SPRINT_VERSION_COLOR = "blue";
    public static final int SPRINT_LENGTH = 14;
    public static final LocalDate SPRINT_START_DATE = LocalDate.now();
    public static final int ANOTHER_SPRINT_VERSION_VALUE = 2;
    public static final String ANOTHER_SPRINT_VERSION_ENVIRONMENT_VALUE = "T&I";
    public static final Environment ANOTHER_SPRINT_VERSION_ENVIRONMENT = environment(ANOTHER_SPRINT_VERSION_ENVIRONMENT_VALUE);
    public static final EnvironmentTO ANOTHER_SPRINT_VERSION_ENVIRONMENT_TO = environmentTO(ANOTHER_SPRINT_VERSION_ENVIRONMENT_VALUE);
    public static final String ANOTHER_SPRINT_VERSION_COLOR = "red";
    public static final int ANOTHER_SPRINT_LENGTH = 5;
    public static final LocalDate ANOTHER_SPRINT_START_DATE = LocalDate.now().plusMonths(1);

    public static Project project() {
        return projectBuilder()
            .build();
    }

    public static Project.Builder projectBuilder() {
        return new Project.Builder()
            .withProjectId(PROJECT_ID)
            .withName(PROJECT_NAME)
            .withSpecifications(List.of(sprintBasedReleaseSpecification()));
    }

    public static Project anotherProject() {
        return new Project.Builder()
            .withProjectId(ANOTHER_PROJECT_ID)
            .withName(ANOTHER_PROJECT_NAME)
            .withSpecifications(List.of(anotherSprintBasedReleaseSpecification()))
            .build();
    }

    public static SprintBasedReleaseSpecification sprintBasedReleaseSpecification() {
        return new SprintBasedReleaseSpecification.Builder()
            .withVersion(sprintBasedVersion())
            .withSprintLength(SPRINT_LENGTH)
            .withStartDate(SPRINT_START_DATE)
            .build();
    }

    public static SprintBasedVersion sprintBasedVersion() {
        return new SprintBasedVersion.Builder()
            .withValue(SPRINT_VERSION_VALUE)
            .withEnvironment(SPRINT_VERSION_ENVIRONMENT)
            .withColor(SPRINT_VERSION_COLOR)
            .build();
    }

    public static SprintBasedReleaseSpecification anotherSprintBasedReleaseSpecification() {
        return new SprintBasedReleaseSpecification.Builder()
            .withVersion(anotherSprintBasedVersion())
            .withSprintLength(ANOTHER_SPRINT_LENGTH)
            .withStartDate(ANOTHER_SPRINT_START_DATE)
            .build();
    }

    public static SprintBasedVersion anotherSprintBasedVersion() {
        return new SprintBasedVersion.Builder()
            .withValue(ANOTHER_SPRINT_VERSION_VALUE)
            .withEnvironment(ANOTHER_SPRINT_VERSION_ENVIRONMENT)
            .withColor(ANOTHER_SPRINT_VERSION_COLOR)
            .build();
    }

    public static ProjectTO projectTO() {
        return new ProjectTO.Builder()
            .withProjectId(PROJECT_ID_VALUE)
            .withName(PROJECT_NAME)
            .withSpecifications(List.of(releaseSpecificationTO()))
            .build();
    }

    public static ProjectTO anotherProjectTO() {
        return new ProjectTO.Builder()
            .withProjectId(ANOTHER_PROJECT_ID_VALUE)
            .withName(ANOTHER_PROJECT_NAME)
            .withSpecifications(List.of(releaseSpecificationTO()))
            .build();
    }

    public static ReleaseSpecificationTO releaseSpecificationTO() {
        return new ReleaseSpecificationTO.Builder()
            .withSprintBased(sprintBasedReleaseSpecificationTO())
            .build();
    }

    public static SprintBasedReleaseSpecificationTO sprintBasedReleaseSpecificationTO() {
        return new SprintBasedReleaseSpecificationTO.Builder()
            .withVersion(sprintBasedVersionTO())
            .withSprintLength(SPRINT_LENGTH)
            .withStartDate(SPRINT_START_DATE)
            .build();
    }

    public static SprintBasedVersionTO sprintBasedVersionTO() {
        return new SprintBasedVersionTO.Builder()
            .withValue(SPRINT_VERSION_VALUE)
            .withEnvironment(SPRINT_VERSION_ENVIRONMENT_TO)
            .withColor(SPRINT_VERSION_COLOR)
            .build();
    }

    public static ReleaseSpecificationTO anotherReleaseSpecificationTO() {
        return new ReleaseSpecificationTO.Builder()
            .withSprintBased(anotherSprintBasedReleaseSpecificationTO())
            .build();
    }

    public static SprintBasedReleaseSpecificationTO anotherSprintBasedReleaseSpecificationTO() {
        return new SprintBasedReleaseSpecificationTO.Builder()
            .withVersion(anotherSprintBasedVersionTO())
            .withSprintLength(ANOTHER_SPRINT_LENGTH)
            .withStartDate(ANOTHER_SPRINT_START_DATE)
            .build();
    }

    public static SprintBasedVersionTO anotherSprintBasedVersionTO() {
        return new SprintBasedVersionTO.Builder()
            .withValue(ANOTHER_SPRINT_VERSION_VALUE)
            .withEnvironment(ANOTHER_SPRINT_VERSION_ENVIRONMENT_TO)
            .withColor(ANOTHER_SPRINT_VERSION_COLOR)
            .build();
    }
}
