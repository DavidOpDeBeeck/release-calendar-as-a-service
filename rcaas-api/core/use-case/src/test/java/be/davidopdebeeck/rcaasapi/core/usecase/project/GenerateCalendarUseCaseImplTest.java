package be.davidopdebeeck.rcaasapi.core.usecase.project;

import be.davidopdebeeck.rcaasapi.core.usecase.UseCaseTest;
import be.davidopdebeeck.rcaasapi.core.usecase.stubs.ProjectTestRepository;
import be.davidopdebeeck.rcaasapi.transferobject.project.calendar.CalendarTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.calendar.DayTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.calendar.WeekTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Optional;

import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.PROJECT_ID_VALUE;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.projectBuilder;
import static java.time.LocalDate.of;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

@UseCaseTest
class GenerateCalendarUseCaseImplTest {

    private static final YearMonth JAN_2023 = YearMonth.of(2023, 1);

    @Autowired
    private GenerateCalendarUseCaseImpl useCase;
    @Autowired
    private ProjectTestRepository repository;

    @Test
    void generateCalendar() {
        repository.save(projectBuilder()
            .withSpecifications(emptyList())
            .build());

        WeekTO week1 = week(
            weekDay(of(2022, 12, 26), true),
            weekDay(of(2022, 12, 27), true),
            weekDay(of(2022, 12, 28), true),
            weekDay(of(2022, 12, 29), true),
            weekDay(of(2022, 12, 30), true),
            weekendDay(of(2022, 12, 31), true),
            weekendDay(of(2023, 1, 1), false)
        );
        WeekTO week2 = week(
            weekDay(of(2023, 1, 2), false),
            weekDay(of(2023, 1, 3), false),
            weekDay(of(2023, 1, 4), false),
            weekDay(of(2023, 1, 5), false),
            weekDay(of(2023, 1, 6), false),
            weekendDay(of(2023, 1, 7), false),
            weekendDay(of(2023, 1, 8), false)
        );
        WeekTO week3 = week(
            weekDay(of(2023, 1, 9), false),
            weekDay(of(2023, 1, 10), false),
            weekDay(of(2023, 1, 11), false),
            weekDay(of(2023, 1, 12), false),
            weekDay(of(2023, 1, 13), false),
            weekendDay(of(2023, 1, 14), false),
            weekendDay(of(2023, 1, 15), false)
        );
        WeekTO week4 = week(
            weekDay(of(2023, 1, 16), false),
            weekDay(of(2023, 1, 17), false),
            weekDay(of(2023, 1, 18), false),
            weekDay(of(2023, 1, 19), false),
            weekDay(of(2023, 1, 20), false),
            weekendDay(of(2023, 1, 21), false),
            weekendDay(of(2023, 1, 22), false)
        );
        WeekTO week5 = week(
            weekDay(of(2023, 1, 23), false),
            weekDay(of(2023, 1, 24), false),
            weekDay(of(2023, 1, 25), false),
            weekDay(of(2023, 1, 26), false),
            weekDay(of(2023, 1, 27), false),
            weekendDay(of(2023, 1, 28), false),
            weekendDay(of(2023, 1, 29), false)
        );
        WeekTO week6 = week(
            weekDay(of(2023, 1, 30), false),
            weekDay(of(2023, 1, 31), false),
            weekDay(of(2023, 2, 1), true),
            weekDay(of(2023, 2, 2), true),
            weekDay(of(2023, 2, 3), true),
            weekendDay(of(2023, 2, 4), true),
            weekendDay(of(2023, 2, 5), true)
        );

        assertThat(useCase.generateCalendar(PROJECT_ID_VALUE, JAN_2023))
            .usingRecursiveComparison()
            .isEqualTo(Optional.of(calendar(week1, week2, week3, week4, week5, week6)));
    }

    private CalendarTO calendar(WeekTO... weeks) {
        return new CalendarTO.Builder()
            .withWeeks(asList(weeks))
            .build();
    }

    private WeekTO week(DayTO... days) {
        return new WeekTO.Builder()
            .withDays(asList(days))
            .build();
    }

    private DayTO weekDay(LocalDate date, boolean otherMonth) {
        return new DayTO.Builder()
            .withDate(date)
            .withToday(false)
            .withWeekend(false)
            .withOtherMonth(otherMonth)
            .withReleases(emptyList())
            .withVersions(emptyList())
            .build();
    }

    private DayTO weekendDay(LocalDate date, boolean otherMonth) {
        return new DayTO.Builder()
            .withDate(date)
            .withToday(false)
            .withWeekend(true)
            .withOtherMonth(otherMonth)
            .withReleases(emptyList())
            .withVersions(emptyList())
            .build();
    }
}