package be.davidopdebeeck.rcaasapi.drivingport.project;

import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectIdTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.UpdateProjectTO;

public interface UpdateProjectUseCase {

    ProjectIdTO updateProject(String projectId, UpdateProjectTO updateProjectTO);
}
