package be.davidopdebeeck.rcaasapi.drivenport;

import be.davidopdebeeck.rcaasapi.core.domain.project.Project;
import be.davidopdebeeck.rcaasapi.core.domain.project.ProjectId;

import java.util.Optional;

public interface ProjectRepository {

    Optional<Project> findBy(ProjectId projectId);

    void save(Project project);
}
