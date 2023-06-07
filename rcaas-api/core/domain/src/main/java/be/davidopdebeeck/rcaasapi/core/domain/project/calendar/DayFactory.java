package be.davidopdebeeck.rcaasapi.core.domain.project.calendar;

import be.davidopdebeeck.rcaasapi.core.domain.project.Project;
import be.davidopdebeeck.rcaasapi.core.domain.project.release.Release;
import be.davidopdebeeck.rcaasapi.core.domain.project.version.Version;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class DayFactory {

    private final Project project;

    public DayFactory(Project project) {
        this.project = project;
    }

    public Day create(LocalDate date, YearMonth yearMonth) {
        return new Day.Builder()
            .withDate(date)
            .withOtherMonth(isOtherMonth(date, yearMonth))
            .withVersions(determineVersions(date))
            .withReleases(determineReleases(date))
            .build();
    }

    private boolean isOtherMonth(LocalDate date, YearMonth yearMonth) {
        return !YearMonth.from(date).equals(yearMonth);
    }

    private List<Version> determineVersions(LocalDate date) {
        return project.getSpecifications().stream()
            .flatMap(specification -> specification.determineVersionAtDate(date).stream())
            .toList();
    }

    private List<Release> determineReleases(LocalDate date) {
        return project.getSpecifications().stream()
            .flatMap(specification -> specification.determineReleaseAtDate(date).stream())
            .toList();
    }
}
