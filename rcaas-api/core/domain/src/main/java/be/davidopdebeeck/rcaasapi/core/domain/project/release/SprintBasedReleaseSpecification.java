package be.davidopdebeeck.rcaasapi.core.domain.project.release;

import be.davidopdebeeck.rcaasapi.core.domain.project.version.SprintBasedVersion;
import be.davidopdebeeck.rcaasapi.core.domain.project.version.Version;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static be.davidopdebeeck.rcaasapi.core.domain.project.release.Rescheduling.merge;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.util.Objects.requireNonNull;
import static java.util.Optional.empty;
import static java.util.Optional.of;

public class SprintBasedReleaseSpecification implements ReleaseSpecification {

    private final SprintBasedVersion version;
    private final LocalDate startDate;
    private final int sprintLength;
    private final List<Rescheduling> reschedulings;

    private SprintBasedReleaseSpecification(Builder builder) {
        version = requireNonNull(builder.version);
        startDate = requireNonNull(builder.startDate);
        sprintLength = requireNonNull(builder.sprintLength);
        reschedulings = merge(requireNonNull(builder.reschedulings));
    }

    @Override
    public Optional<Version> determineVersionAtDate(LocalDate date) {
        if (date.isBefore(startDate)) {
            return empty();
        }

        if (isRescheduled(date)) {
            return determineVersionAtDate(date.minusDays(1));
        }

        long daysBetween = DAYS.between(startDate, date);
        long versionsBetween = daysBetween / sprintLength;
        long rescheduledVersionsBefore = calculateRescheduledVersionsBefore(date);
        return of(version.increase(versionsBetween - rescheduledVersionsBefore));
    }

    @Override
    public Optional<Release> determineReleaseAtDate(LocalDate date) {
        LocalDate dayBefore = date.minusDays(1);
        Optional<Version> versionOnDayBefore = determineVersionAtDate(dayBefore);
        Optional<Version> versionOnDay = determineVersionAtDate(date);
        if (versionOnDayBefore.equals(versionOnDay)) {
            return empty();
        }
        return versionOnDay.map(Release::release);
    }

    private boolean isRescheduled(LocalDate date) {
        return reschedulings.stream().anyMatch(rescheduling -> {
            LocalDate fromDate = rescheduling.getFrom();
            LocalDate toDate = rescheduling.getTo();
            return date.equals(fromDate) || (date.isAfter(fromDate) && date.isBefore(toDate));
        });
    }

    private long calculateRescheduledVersionsBefore(LocalDate date) {
        return reschedulings.stream()
            .filter(rescheduling -> date.equals(rescheduling.getTo()) || date.isAfter(rescheduling.getTo()))
            .filter(rescheduling -> rescheduling.getDaysBetween() >= sprintLength)
            .mapToLong(rescheduling -> rescheduling.getDaysBetween() / sprintLength)
            .sum();
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

    public List<Rescheduling> getReschedulings() {
        return reschedulings;
    }

    public static final class Builder {

        private SprintBasedVersion version;
        private LocalDate startDate;
        private Integer sprintLength;
        private List<Rescheduling> reschedulings;

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

        public Builder withReschedulings(List<Rescheduling> reschedulings) {
            this.reschedulings = reschedulings;
            return this;
        }

        public SprintBasedReleaseSpecification build() {
            return new SprintBasedReleaseSpecification(this);
        }
    }
}
