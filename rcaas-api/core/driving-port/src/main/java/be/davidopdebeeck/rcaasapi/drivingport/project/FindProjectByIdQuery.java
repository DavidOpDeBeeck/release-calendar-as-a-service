package be.davidopdebeeck.rcaasapi.drivingport.project;

import app.dodb.smd.api.query.Query;
import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectTO;

import java.util.Optional;

public record FindProjectByIdQuery(String projectId) implements Query<Optional<ProjectTO>> {
}
