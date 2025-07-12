package be.davidopdebeeck.rcaasapi.drivingport.project;

import app.dodb.smd.api.command.Command;
import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectIdTO;

import java.util.Optional;

import static java.util.Optional.ofNullable;

public record CreateProjectCommand(String name) implements Command<ProjectIdTO> {

    public Optional<String> getName() {
        return ofNullable(name);
    }
}
