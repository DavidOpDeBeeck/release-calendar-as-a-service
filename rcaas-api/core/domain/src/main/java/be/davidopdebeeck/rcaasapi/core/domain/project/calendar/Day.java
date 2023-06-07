package be.davidopdebeeck.rcaasapi.core.domain.project.calendar;

import be.davidopdebeeck.rcaasapi.core.domain.project.release.Release;
import be.davidopdebeeck.rcaasapi.core.domain.project.version.Version;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;

import static java.time.DayOfWeek.SATURDAY;
import static java.time.DayOfWeek.SUNDAY;
import static java.util.Objects.requireNonNull;

public class Day {

    private final LocalDate date;
    private final boolean otherMonth;
    private final List<Version> versions;
    private final List<Release> releases;

    private Day(Builder builder) {
        date = requireNonNull(builder.date);
        otherMonth = requireNonNull(builder.otherMonth);
        versions = requireNonNull(builder.versions);
        releases = requireNonNull(builder.releases);
    }

    public LocalDate getDate() {
        return date;
    }

    public boolean isWeekend() {
        return EnumSet.of(SATURDAY, SUNDAY).contains(date.getDayOfWeek());
    }

    public boolean isToday() {
        return date.equals(LocalDate.now());
    }

    public boolean isOtherMonth() {
        return otherMonth;
    }

    public List<Version> getVersions() {
        return versions;
    }

    public List<Release> getReleases() {
        return releases;
    }

    public static final class Builder {

        private LocalDate date;
        private Boolean otherMonth;
        private List<Version> versions;
        private List<Release> releases;

        public Builder withDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public Builder withOtherMonth(boolean otherMonth) {
            this.otherMonth = otherMonth;
            return this;
        }

        public Builder withVersions(List<Version> versions) {
            this.versions = versions;
            return this;
        }

        public Builder withReleases(List<Release> releases) {
            this.releases = releases;
            return this;
        }

        public Day build() {
            return new Day(this);
        }
    }
}
