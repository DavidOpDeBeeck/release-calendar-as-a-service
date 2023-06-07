package be.davidopdebeeck.rcaasapi.core.usecase.project;

import be.davidopdebeeck.rcaasapi.core.domain.project.Project;
import be.davidopdebeeck.rcaasapi.core.domain.project.UpdateProject;
import be.davidopdebeeck.rcaasapi.core.domain.project.release.ReleaseSpecification;
import be.davidopdebeeck.rcaasapi.core.domain.project.release.SprintBasedReleaseSpecification;
import be.davidopdebeeck.rcaasapi.core.domain.project.version.SprintBasedVersion;
import be.davidopdebeeck.rcaasapi.drivenport.ProjectRepository;
import be.davidopdebeeck.rcaasapi.drivingport.project.UpdateProjectUseCase;
import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectIdTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.UpdateProjectTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.release.ReleaseSpecificationTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.release.SprintBasedReleaseSpecificationTO;
import org.springframework.stereotype.Component;

import java.util.List;

import static be.davidopdebeeck.rcaasapi.core.domain.project.ProjectId.projectId;
import static be.davidopdebeeck.rcaasapi.core.domain.project.environment.Environment.environment;

@Component
public class UpdateProjectUseCaseImpl implements UpdateProjectUseCase {

    private final ProjectRepository repository;

    public UpdateProjectUseCaseImpl(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProjectIdTO updateProject(String projectId, UpdateProjectTO updateProjectTO) {
        Project project = repository.findBy(projectId(projectId)).orElseThrow();

        project.update(new UpdateProject.Builder()
            .withName(updateProjectTO.getName().orElse(null))
            .withSpecifications(mapSpecifications(updateProjectTO.getSpecifications()))
            .build());
        repository.save(project);

        return new ProjectIdTO.Builder()
            .withProjectId(project.getProjectId().getValue())
            .build();
    }

    private List<ReleaseSpecification> mapSpecifications(List<ReleaseSpecificationTO> specificationTOs) {
        return specificationTOs.stream()
            .map(this::mapSpecification)
            .toList();
    }

    private ReleaseSpecification mapSpecification(ReleaseSpecificationTO releaseSpecificationTO) {
        SprintBasedReleaseSpecificationTO sprintBased = releaseSpecificationTO.getSprintBased();
        return new SprintBasedReleaseSpecification.Builder()
            .withVersion(new SprintBasedVersion.Builder()
                .withValue(sprintBased.getVersion().getValue())
                .withColor(sprintBased.getVersion().getColor())
                .withEnvironment(environment(sprintBased.getVersion().getEnvironment().getValue()))
                .build())
            .withStartDate(sprintBased.getStartDate())
            .withSprintLength(sprintBased.getSprintLength())
            .build();
    }
}
