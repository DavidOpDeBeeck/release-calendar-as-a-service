package be.davidopdebeeck.rcaasapi.core.domain.project.calendar;

import be.davidopdebeeck.rcaasapi.core.domain.project.Project;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.IntStream.range;

public class CalendarFactory {

    private final DayFactory dayFactory;

    public CalendarFactory(Project project) {
        this.dayFactory = new DayFactory(project);
    }

    public Calendar create(YearMonth yearMonth) {
        List<Week> weeks = new ArrayList<>();

        LocalDate date = yearMonth.atDay(1);
        while (weeks.size() < 6) {
            Week week = createWeekFrom(date, yearMonth);
            weeks.add(week);
            date = week.getFirstDayOfNextWeek();
        }

        return new Calendar.Builder()
            .withWeeks(weeks)
            .build();
    }

    private Week createWeekFrom(LocalDate date, YearMonth yearMonth) {
        LocalDate monday = date.minusDays(date.getDayOfWeek().getValue() - 1L);

        return new Week.Builder()
            .withDays(range(0, 7)
                .mapToObj(monday::plusDays)
                .map(dayOfWeek -> dayFactory.create(dayOfWeek, yearMonth))
                .toList())
            .build();
    }
}
