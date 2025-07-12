package be.davidopdebeeck.rcaasapi.core.usecase.project;

import app.dodb.smd.api.command.CommandHandler;
import be.davidopdebeeck.rcaasapi.core.domain.project.Project;
import be.davidopdebeeck.rcaasapi.core.domain.project.UpdateProject;
import be.davidopdebeeck.rcaasapi.core.domain.project.release.ReleaseSpecification;
import be.davidopdebeeck.rcaasapi.core.domain.project.release.Rescheduling;
import be.davidopdebeeck.rcaasapi.core.domain.project.release.SprintBasedReleaseSpecification;
import be.davidopdebeeck.rcaasapi.core.domain.project.version.SprintBasedVersion;
import be.davidopdebeeck.rcaasapi.drivenport.ProjectRepository;
import be.davidopdebeeck.rcaasapi.drivingport.project.UpdateProjectCommand;
import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectIdTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.release.ReleaseSpecificationTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.release.SprintBasedReleaseSpecificationTO;
import org.springframework.stereotype.Component;

import java.util.List;

import static be.davidopdebeeck.rcaasapi.core.domain.project.ProjectId.projectId;
import static be.davidopdebeeck.rcaasapi.core.domain.project.environment.Environment.environment;

@Component
public class UpdateProjectUseCase {

    private final ProjectRepository repository;

    public UpdateProjectUseCase(ProjectRepository repository) {
        this.repository = repository;
    }

    @CommandHandler
    public ProjectIdTO handle(UpdateProjectCommand command) {
        Project project = repository.findBy(projectId(command.projectId())).orElseThrow();

        project.update(new UpdateProject.Builder()
            .withName(command.getName().orElse(null))
            .withSpecifications(mapSpecifications(command.specifications()))
            .build());
        repository.save(project);

        return new ProjectIdTO.Builder()
            .withProjectId(project.getProjectId().getValue())
            .build();
    }

    private List<ReleaseSpecification> mapSpecifications(List<ReleaseSpecificationTO> specificationTOs) {
        return specificationTOs.stream()
            .map(ReleaseSpecificationTO::getSprintBased)
            .map(this::mapSpecification)
            .toList();
    }

    private ReleaseSpecification mapSpecification(SprintBasedReleaseSpecificationTO sprintBased) {
        List<Rescheduling> reschedulings = sprintBased.getReschedulings().stream()
            .map(reschedulingTO -> new Rescheduling.Builder()
                .withFrom(reschedulingTO.getFrom())
                .withTo(reschedulingTO.getTo())
                .build())
            .toList();

        return new SprintBasedReleaseSpecification.Builder()
            .withVersion(new SprintBasedVersion.Builder()
                .withValue(sprintBased.getVersion().getValue())
                .withColor(sprintBased.getVersion().getColor())
                .withEnvironment(environment(sprintBased.getVersion().getEnvironment().getValue()))
                .build())
            .withStartDate(sprintBased.getStartDate())
            .withSprintLength(sprintBased.getSprintLength())
            .withReschedulings(reschedulings)
            .build();
    }
}
