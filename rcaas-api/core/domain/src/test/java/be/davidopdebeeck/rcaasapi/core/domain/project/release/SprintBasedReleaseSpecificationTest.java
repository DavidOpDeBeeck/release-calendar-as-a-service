package be.davidopdebeeck.rcaasapi.core.domain.project.release;

import be.davidopdebeeck.rcaasapi.core.domain.project.version.SprintBasedVersion;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static be.davidopdebeeck.rcaasapi.core.domain.project.environment.Environment.environment;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.SPRINT_LENGTH;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.SPRINT_START_DATE;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.SPRINT_VERSION_COLOR;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.SPRINT_VERSION_ENVIRONMENT_VALUE;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.sprintBasedVersion;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SprintBasedReleaseSpecificationTest {

    @Test
    void allFieldsAreRequired() {
        assertThatThrownBy(() -> new SprintBasedReleaseSpecification.Builder()
            .withStartDate(null)
            .withVersion(sprintBasedVersion())
            .withSprintLength(SPRINT_LENGTH)
            .build()).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new SprintBasedReleaseSpecification.Builder()
            .withStartDate(SPRINT_START_DATE)
            .withVersion(null)
            .withSprintLength(SPRINT_LENGTH)
            .build()).isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> new SprintBasedReleaseSpecification.Builder()
            .withStartDate(SPRINT_START_DATE)
            .withVersion(sprintBasedVersion())
            .build()).isInstanceOf(NullPointerException.class);
    }

    @Test
    void determineVersionAtDate() {
        SprintBasedReleaseSpecification specification = new SprintBasedReleaseSpecification.Builder()
            .withStartDate(LocalDate.of(2023, 1, 1))
            .withVersion(version(1))
            .withSprintLength(2)
            .build();

        assertThat(specification.determineVersionAtDate(LocalDate.of(2022, 12, 31))).isEmpty();
        assertThat(specification.determineVersionAtDate(LocalDate.of(2023, 1, 1))).contains(version(1));
        assertThat(specification.determineVersionAtDate(LocalDate.of(2023, 1, 2))).contains(version(1));
        assertThat(specification.determineVersionAtDate(LocalDate.of(2023, 1, 3))).contains(version(2));
        assertThat(specification.determineVersionAtDate(LocalDate.of(2023, 1, 4))).contains(version(2));
    }

    @Test
    void determineReleaseAtDate() {
        SprintBasedReleaseSpecification specification = new SprintBasedReleaseSpecification.Builder()
            .withStartDate(LocalDate.of(2023, 1, 1))
            .withVersion(version(1))
            .withSprintLength(2)
            .build();

        assertThat(specification.determineReleaseAtDate(LocalDate.of(2022, 12, 31))).isEmpty();
        assertThat(specification.determineReleaseAtDate(LocalDate.of(2023, 1, 1))).contains(release(1));
        assertThat(specification.determineReleaseAtDate(LocalDate.of(2023, 1, 2))).isEmpty();
        assertThat(specification.determineReleaseAtDate(LocalDate.of(2023, 1, 3))).contains(release(2));
        assertThat(specification.determineReleaseAtDate(LocalDate.of(2023, 1, 4))).isEmpty();
    }

    private Release release(int value) {
        return new Release.Builder()
            .withVersion(new SprintBasedVersion.Builder()
                .withValue(value)
                .withColor(SPRINT_VERSION_COLOR)
                .withEnvironment(environment(SPRINT_VERSION_ENVIRONMENT_VALUE))
                .build())
            .build();
    }

    private SprintBasedVersion version(int value) {
        return new SprintBasedVersion.Builder()
            .withValue(value)
            .withColor(SPRINT_VERSION_COLOR)
            .withEnvironment(environment(SPRINT_VERSION_ENVIRONMENT_VALUE))
            .build();
    }
}