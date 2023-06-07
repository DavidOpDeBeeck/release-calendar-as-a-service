package be.davidopdebeeck.rcaasapi.core.usecase.project;

import be.davidopdebeeck.rcaasapi.core.usecase.project.calendar.CalendarTOMapper;
import be.davidopdebeeck.rcaasapi.drivenport.ProjectRepository;
import be.davidopdebeeck.rcaasapi.drivingport.project.GenerateCalendarUseCase;
import be.davidopdebeeck.rcaasapi.transferobject.project.calendar.CalendarTO;
import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.util.Optional;

import static be.davidopdebeeck.rcaasapi.core.domain.project.ProjectId.projectId;

@Component
public class GenerateCalendarUseCaseImpl implements GenerateCalendarUseCase {

    private final ProjectRepository repository;
    private final CalendarTOMapper calendarTOMapper;

    public GenerateCalendarUseCaseImpl(ProjectRepository repository,
                                       CalendarTOMapper calendarTOMapper) {
        this.repository = repository;
        this.calendarTOMapper = calendarTOMapper;
    }

    @Override
    public Optional<CalendarTO> generateCalendar(String projectId, YearMonth yearMonth) {
        return repository.findBy(projectId(projectId))
            .map(project -> project.createCalendarFor(yearMonth))
            .map(calendarTOMapper::mapCalendar);
    }
}
