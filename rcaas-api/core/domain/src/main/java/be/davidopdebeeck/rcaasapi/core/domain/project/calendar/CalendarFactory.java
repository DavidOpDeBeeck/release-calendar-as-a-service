package be.davidopdebeeck.rcaasapi.core.domain.project.calendar;

import be.davidopdebeeck.rcaasapi.core.domain.project.Project;

import java.time.LocalDate;
import java.time.YearMonth;

import static java.util.stream.LongStream.range;

public class CalendarFactory {

    private static final long DAYS_IN_WEEK = 7L;
    private static final long WEEKS_IN_MONTH = 6L;

    private final DayFactory dayFactory;

    public CalendarFactory(Project project) {
        this.dayFactory = new DayFactory(project);
    }

    public Calendar create(YearMonth yearMonth) {
        LocalDate firstDayOfMonth = yearMonth.atDay(1);
        return new Calendar.Builder()
            .withWeeks(range(0, WEEKS_IN_MONTH)
                .mapToObj(weekIndex -> firstDayOfMonth.plusDays(DAYS_IN_WEEK * weekIndex))
                .map(date -> createWeekFrom(date, yearMonth))
                .toList())
            .build();
    }

    private Week createWeekFrom(LocalDate date, YearMonth yearMonth) {
        LocalDate monday = date.minusDays(date.getDayOfWeek().getValue() - 1L);
        return new Week.Builder()
            .withDays(range(0, DAYS_IN_WEEK)
                .mapToObj(monday::plusDays)
                .map(dayOfWeek -> dayFactory.create(dayOfWeek, yearMonth))
                .toList())
            .build();
    }
}
