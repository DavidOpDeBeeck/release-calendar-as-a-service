package be.davidopdebeeck.rcaasapi.drivingport.project;

import be.davidopdebeeck.rcaasapi.transferobject.project.calendar.CalendarTO;

import java.time.YearMonth;
import java.util.Optional;

public interface GenerateCalendarUseCase {

    Optional<CalendarTO> generateCalendar(String projectId, YearMonth yearMonth);
}
