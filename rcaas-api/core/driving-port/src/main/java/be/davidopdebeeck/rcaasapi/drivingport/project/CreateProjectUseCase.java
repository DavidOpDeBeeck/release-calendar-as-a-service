package be.davidopdebeeck.rcaasapi.drivingport.project;

import be.davidopdebeeck.rcaasapi.transferobject.project.CreateProjectTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectIdTO;

public interface CreateProjectUseCase {

    ProjectIdTO createProject(CreateProjectTO createProjectTO);
}
