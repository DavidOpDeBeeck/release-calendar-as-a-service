package be.davidopdebeeck.rcaasapi.core.usecase.project.calendar;

import be.davidopdebeeck.rcaasapi.core.domain.project.calendar.Calendar;
import be.davidopdebeeck.rcaasapi.core.domain.project.calendar.Day;
import be.davidopdebeeck.rcaasapi.core.domain.project.calendar.Week;
import be.davidopdebeeck.rcaasapi.core.domain.project.release.Release;
import be.davidopdebeeck.rcaasapi.core.domain.project.version.Version;
import be.davidopdebeeck.rcaasapi.transferobject.project.calendar.CalendarTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.calendar.DayTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.calendar.WeekTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.release.ReleaseTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.version.VersionTO;
import org.springframework.stereotype.Component;

import static be.davidopdebeeck.rcaasapi.transferobject.project.environment.EnvironmentTO.environmentTO;

@Component
public class CalendarTOMapper {

    public CalendarTO mapCalendar(Calendar calendar) {
        return new CalendarTO.Builder()
            .withWeeks(calendar.getWeeks().stream()
                .map(this::mapWeek)
                .toList())
            .build();
    }

    private WeekTO mapWeek(Week week) {
        return new WeekTO.Builder()
            .withDays(week.getDays().stream()
                .map(this::mapDay)
                .toList())
            .build();
    }

    private DayTO mapDay(Day day) {
        return new DayTO.Builder()
            .withDate(day.getDate())
            .withToday(day.isToday())
            .withWeekend(day.isWeekend())
            .withOtherMonth(day.isOtherMonth())
            .withReleases(day.getReleases().stream()
                .map(this::mapRelease)
                .toList())
            .withVersions(day.getVersions().stream()
                .map(this::mapVersion)
                .toList())
            .build();
    }

    private ReleaseTO mapRelease(Release release) {
        return new ReleaseTO.Builder()
            .withVersion(mapVersion(release.getVersion()))
            .build();
    }

    private VersionTO mapVersion(Version version) {
        return new VersionTO.Builder()
            .withValue(version.getValue())
            .withColor(version.getColor())
            .withEnvironment(environmentTO(version.getEnvironment().getValue()))
            .build();
    }
}
