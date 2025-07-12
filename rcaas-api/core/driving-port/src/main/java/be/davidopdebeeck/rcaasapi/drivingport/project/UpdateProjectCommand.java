package be.davidopdebeeck.rcaasapi.drivingport.project;

import app.dodb.smd.api.command.Command;
import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectIdTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.release.ReleaseSpecificationTO;

import java.util.List;
import java.util.Optional;

public record UpdateProjectCommand(String projectId, String name, List<ReleaseSpecificationTO> specifications) implements Command<ProjectIdTO> {

    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }
}
