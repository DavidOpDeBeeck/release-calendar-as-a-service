package be.davidopdebeeck.rcaasapi.drivingadapter.project;

import app.dodb.smd.api.command.CommandGateway;
import app.dodb.smd.api.query.QueryGateway;
import be.davidopdebeeck.rcaasapi.drivingport.project.CreateProjectCommand;
import be.davidopdebeeck.rcaasapi.drivingport.project.FindProjectByIdQuery;
import be.davidopdebeeck.rcaasapi.drivingport.project.GenerateCalendarQuery;
import be.davidopdebeeck.rcaasapi.drivingport.project.UpdateProjectCommand;
import be.davidopdebeeck.rcaasapi.transferobject.project.CreateProjectTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectIdTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.UpdateProjectTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.calendar.CalendarTO;
import org.springframework.http.ResponseEntity;
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
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.http.ResponseEntity.status;


@RestController
@RequestMapping("/api/v1/projects")
public class ProjectController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public ProjectController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @PostMapping
    public ResponseEntity<ProjectIdTO> postProject(@RequestBody CreateProjectTO createProjectTO) {
        return status(CREATED)
            .body(commandGateway.send(new CreateProjectCommand(createProjectTO.getName().orElse(null))));
    }

    @PutMapping(value = "/{projectId}")
    public ResponseEntity<ProjectIdTO> updateProject(@PathVariable("projectId") String projectId,
                                                     @RequestBody UpdateProjectTO updateProjectTO) {
        return ok(commandGateway.send(new UpdateProjectCommand(projectId, updateProjectTO.getName().orElse(null), updateProjectTO.getSpecifications())));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectTO> getProject(@PathVariable("projectId") String projectId) {
        return ResponseEntity.of(queryGateway.send(new FindProjectByIdQuery(projectId)));
    }

    @GetMapping("/{projectId}/calendar")
    public ResponseEntity<CalendarTO> getProjectCalendar(@PathVariable("projectId") String projectId,
                                                         @RequestParam("yearMonth") YearMonth yearMonth) {
        return ResponseEntity.of(queryGateway.send(new GenerateCalendarQuery(projectId, yearMonth)));
    }
}

