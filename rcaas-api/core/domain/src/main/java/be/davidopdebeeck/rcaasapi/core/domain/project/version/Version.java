package be.davidopdebeeck.rcaasapi.core.domain.project.version;

import be.davidopdebeeck.rcaasapi.core.domain.project.environment.Environment;

public sealed interface Version
    permits SprintBasedVersion {

    String getValue();

    String getColor();

    Environment getEnvironment();
}
