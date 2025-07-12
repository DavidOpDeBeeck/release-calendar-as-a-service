package be.davidopdebeeck.rcaasapi.core.usecase.stubs;

import app.dodb.smd.spring.test.scope.annotation.SMDTestScope;
import be.davidopdebeeck.rcaasapi.core.domain.project.Project;
import be.davidopdebeeck.rcaasapi.core.domain.project.ProjectId;
import be.davidopdebeeck.rcaasapi.core.domain.project.release.ReleaseSpecification;
import be.davidopdebeeck.rcaasapi.core.domain.project.release.SprintBasedReleaseSpecification;
import be.davidopdebeeck.rcaasapi.drivenport.ProjectQueryRepository;
import be.davidopdebeeck.rcaasapi.drivenport.ProjectRepository;
import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.release.ReleaseSpecificationTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.release.ReschedulingTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.release.SprintBasedReleaseSpecificationTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.version.SprintBasedVersionTO;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static be.davidopdebeeck.rcaasapi.transferobject.project.environment.EnvironmentTO.environmentTO;

@Component
@SMDTestScope
public class ProjectTestRepository implements ProjectRepository, ProjectQueryRepository {

    private final Map<ProjectId, Project> projectsById = new HashMap<>();

    @Override
    public Optional<ProjectTO> queryBy(ProjectId projectId) {
        return findBy(projectId)
            .map(this::mapToProjectTO);
    }

    @Override
    public Optional<Project> findBy(ProjectId projectId) {
        return Optional.ofNullable(projectsById.get(projectId));
    }

    @Override
    public void save(Project project) {
        projectsById.put(project.getProjectId(), project);
    }

    private ProjectTO mapToProjectTO(Project project) {
        return new ProjectTO.Builder()
            .withProjectId(project.getProjectId().getValue())
            .withName(project.getName())
            .withSpecifications(project.getSpecifications().stream()
                .map(this::mapSpecification)
                .toList())
            .build();
    }

    private ReleaseSpecificationTO mapSpecification(ReleaseSpecification specification) {
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
                    .withReschedulings(sprintBased.getReschedulings().stream()
                        .map(rescheduling -> new ReschedulingTO.Builder()
                            .withFrom(rescheduling.getFrom())
                            .withTo(rescheduling.getTo())
                            .build())
                        .toList())
                    .build())
                .build();
        }
        throw new IllegalStateException("Release specification " + specification + " is currently not supported");
    }
}
