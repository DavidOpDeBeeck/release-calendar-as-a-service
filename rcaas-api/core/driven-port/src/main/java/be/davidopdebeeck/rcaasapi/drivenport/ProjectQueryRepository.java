package be.davidopdebeeck.rcaasapi.drivenport;

import be.davidopdebeeck.rcaasapi.core.domain.project.ProjectId;
import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectTO;

import java.util.Optional;

public interface ProjectQueryRepository {

    Optional<ProjectTO> queryBy(ProjectId projectId);
}
