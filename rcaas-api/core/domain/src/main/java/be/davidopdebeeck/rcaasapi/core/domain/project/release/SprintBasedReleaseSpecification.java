package be.davidopdebeeck.rcaasapi.core.domain.project.release;

import be.davidopdebeeck.rcaasapi.core.domain.project.version.SprintBasedVersion;
import be.davidopdebeeck.rcaasapi.core.domain.project.version.Version;

import java.time.LocalDate;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public class SprintBasedReleaseSpecification implements ReleaseSpecification {

    private final SprintBasedVersion version;
    private final LocalDate startDate;
    private final int sprintLength;

    private SprintBasedReleaseSpecification(Builder builder) {
        version = requireNonNull(builder.version);
        startDate = requireNonNull(builder.startDate);
        sprintLength = requireNonNull(builder.sprintLength);
    }

    @Override
    public Optional<Version> determineVersionAtDate(LocalDate date) {
        if (date.isBefore(startDate)) {
            return empty();
        }
        long daysBetween = DAYS.between(startDate, date);
        long versionsBetween = daysBetween / sprintLength;
        return of(version.increase(versionsBetween));
    }

    @Override
    public Optional<Release> determineReleaseAtDate(LocalDate date) {
        Optional<Version> versionOnDayBefore = determineVersionAtDate(date.minusDays(1));
        Optional<Version> versionOnDay = determineVersionAtDate(date);
        if (versionOnDayBefore.equals(versionOnDay)) {
            return empty();
        }
        return versionOnDay.map(Release::release);
    }

    public SprintBasedVersion getVersion() {
        return version;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getSprintLength() {
        return sprintLength;
    }

    public static final class Builder {

        private SprintBasedVersion version;
        private LocalDate startDate;
        private Integer sprintLength;

        public Builder withVersion(SprintBasedVersion version) {
            this.version = version;
            return this;
        }

        public Builder withStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder withSprintLength(int sprintLength) {
            this.sprintLength = sprintLength;
            return this;
        }

        public SprintBasedReleaseSpecification build() {
            return new SprintBasedReleaseSpecification(this);
        }
    }
}
