package be.davidopdebeeck.rcaasapi.drivingport.project;

import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectTO;

import java.util.Optional;

public interface FindProjectByIdUseCase {

    Optional<ProjectTO> findProjectById(String projectId);
}
