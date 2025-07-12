package be.davidopdebeeck.rcaasapi.core.usecase.project;

import app.dodb.smd.api.query.QueryHandler;
import be.davidopdebeeck.rcaasapi.core.usecase.project.calendar.CalendarTOMapper;
import be.davidopdebeeck.rcaasapi.drivenport.ProjectRepository;
import be.davidopdebeeck.rcaasapi.drivingport.project.GenerateCalendarQuery;
import be.davidopdebeeck.rcaasapi.transferobject.project.calendar.CalendarTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static be.davidopdebeeck.rcaasapi.core.domain.project.ProjectId.projectId;

@Component
public class GenerateCalendarUseCase {

    private final ProjectRepository repository;
    private final CalendarTOMapper calendarTOMapper;

    public GenerateCalendarUseCase(ProjectRepository repository,
                                   CalendarTOMapper calendarTOMapper) {
        this.repository = repository;
        this.calendarTOMapper = calendarTOMapper;
    }

    @QueryHandler
    public Optional<CalendarTO> handle(GenerateCalendarQuery query) {
        return repository.findBy(projectId(query.projectId()))
            .map(project -> project.createCalendarFor(query.yearMonth()))
            .map(calendarTOMapper::mapCalendar);
    }
}
