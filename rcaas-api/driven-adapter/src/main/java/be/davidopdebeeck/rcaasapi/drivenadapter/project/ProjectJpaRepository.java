package be.davidopdebeeck.rcaasapi.drivenadapter.project;

import be.davidopdebeeck.rcaasapi.core.domain.project.Project;
import be.davidopdebeeck.rcaasapi.core.domain.project.ProjectId;
import be.davidopdebeeck.rcaasapi.core.domain.project.release.ReleaseSpecification;
import be.davidopdebeeck.rcaasapi.core.domain.project.release.SprintBasedReleaseSpecification;
import be.davidopdebeeck.rcaasapi.core.domain.project.version.SprintBasedVersion;
import be.davidopdebeeck.rcaasapi.drivenport.ProjectRepository;
import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.release.ReleaseSpecificationTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.release.SprintBasedReleaseSpecificationTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.version.SprintBasedVersionTO;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import static be.davidopdebeeck.rcaasapi.core.domain.project.environment.Environment.environment;
import static be.davidopdebeeck.rcaasapi.transferobject.project.environment.EnvironmentTO.environmentTO;

@Repository
public class ProjectJpaRepository implements ProjectRepository {

    private final ProjectJpaSpringRepository repository;

    public ProjectJpaRepository(ProjectJpaSpringRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Project> findBy(ProjectId projectId) {
        return repository.findById(projectId.getValue())
            .map(ProjectJpaEntity::getProjectTO)
            .map(calendar -> new Project.Builder()
                .withProjectId(ProjectId.projectId(calendar.getProjectId()))
                .withName(calendar.getName())
                .withSpecifications(calendar.getSpecifications().stream()
                    .map(this::map)
                    .toList())
                .build());
    }

    @Override
    public void save(Project project) {
        repository.save(new ProjectJpaEntity.Builder()
            .withProjectId(project.getProjectId().getValue())
            .withProject(new ProjectTO.Builder()
                .withProjectId(project.getProjectId().getValue())
                .withName(project.getName())
                .withSpecifications(project.getSpecifications().stream()
                    .map(this::map)
                    .toList())
                .build())
            .build());
    }

    private ReleaseSpecificationTO map(ReleaseSpecification specification) {
        if (specification instanceof SprintBasedReleaseSpecification sprintBased) {
            return new ReleaseSpecificationTO.Builder()
                .withSprintBased(new SprintBasedReleaseSpecificationTO.Builder()
                    .withVersion(new SprintBasedVersionTO.Builder()
                        .withValue(sprintBased.getVersion().getValueAsLong())
                        .withColor(sprintBased.getVersion().getColor())
                        .withEnvironment(environmentTO(sprintBased.getVersion().getEnvironment().getValue()))
                        .build())
                    .withStartDate(sprintBased.getStartDate())
                    .withSprintLength(sprintBased.getSprintLength())
                    .build())
                .build();
        }
        throw new IllegalStateException("Release specification " + specification + " is currently not supported");
    }

    private ReleaseSpecification map(ReleaseSpecificationTO specification) {
        return new SprintBasedReleaseSpecification.Builder()
            .withVersion(
                new SprintBasedVersion.Builder()
                    .withValue(specification.getSprintBased().getVersion().getValue())
                    .withColor(specification.getSprintBased().getVersion().getColor())
                    .withEnvironment(environment(specification.getSprintBased().getVersion().getEnvironment().getValue()))
                    .build())
            .withStartDate(specification.getSprintBased().getStartDate())
            .withSprintLength(specification.getSprintBased().getSprintLength())
            .build();
    }
}
