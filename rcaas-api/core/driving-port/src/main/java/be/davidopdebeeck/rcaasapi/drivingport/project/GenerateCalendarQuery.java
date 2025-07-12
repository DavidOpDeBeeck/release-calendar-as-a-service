package be.davidopdebeeck.rcaasapi.drivingport.project;

import app.dodb.smd.api.query.Query;
import be.davidopdebeeck.rcaasapi.transferobject.project.calendar.CalendarTO;

import java.time.YearMonth;
import java.util.Optional;

public record GenerateCalendarQuery(String projectId, YearMonth yearMonth) implements Query<Optional<CalendarTO>> {
}
