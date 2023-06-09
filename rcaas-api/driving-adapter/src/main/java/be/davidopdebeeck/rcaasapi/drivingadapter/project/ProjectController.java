package be.davidopdebeeck.rcaasapi.drivingadapter.project;

import be.davidopdebeeck.rcaasapi.drivingport.project.CreateProjectUseCase;
import be.davidopdebeeck.rcaasapi.drivingport.project.FindProjectByIdUseCase;
import be.davidopdebeeck.rcaasapi.drivingport.project.GenerateCalendarUseCase;
import be.davidopdebeeck.rcaasapi.drivingport.project.UpdateProjectUseCase;
import be.davidopdebeeck.rcaasapi.transferobject.project.CreateProjectTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectIdTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.UpdateProjectTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.calendar.CalendarTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.YearMonth;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;


@CrossOrigin(origins = "https://www.releasecalendar.app")
@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final CreateProjectUseCase createProjectUseCase;
    private final UpdateProjectUseCase updateProjectUseCase;
    private final FindProjectByIdUseCase findProjectByIdUseCase;
    private final GenerateCalendarUseCase generateCalendarUseCase;

    public ProjectController(CreateProjectUseCase createProjectUseCase,
                             UpdateProjectUseCase updateProjectUseCase,
                             FindProjectByIdUseCase findProjectByIdUseCase,
                             GenerateCalendarUseCase generateCalendarUseCase) {
        this.createProjectUseCase = createProjectUseCase;
        this.updateProjectUseCase = updateProjectUseCase;
        this.findProjectByIdUseCase = findProjectByIdUseCase;
        this.generateCalendarUseCase = generateCalendarUseCase;
    }

    @PostMapping
    public ResponseEntity<ProjectIdTO> postProject(@RequestBody CreateProjectTO createProjectTO) {
        return status(CREATED)
            .body(createProjectUseCase.createProject(createProjectTO));
    }

    @PutMapping(value = "/{projectId}")
    public ResponseEntity<ProjectIdTO> updateProject(@PathVariable("projectId") String projectId,
                                                     @RequestBody UpdateProjectTO updateProjectTO) {
        return ok(updateProjectUseCase.updateProject(projectId, updateProjectTO));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectTO> getProject(@PathVariable("projectId") String projectId) {
        return findProjectByIdUseCase.findProjectById(projectId)
            .map(ResponseEntity::ok)
            .orElse(notFound().build());
    }

    @GetMapping("/{projectId}/calendar")
    public ResponseEntity<CalendarTO> getProjectCalendar(@PathVariable("projectId") String projectId,
                                                         @RequestParam("yearMonth") YearMonth yearMonth) {
        return generateCalendarUseCase.generateCalendar(projectId, yearMonth)
            .map(ResponseEntity::ok)
            .orElse(notFound().build());
    }
}

