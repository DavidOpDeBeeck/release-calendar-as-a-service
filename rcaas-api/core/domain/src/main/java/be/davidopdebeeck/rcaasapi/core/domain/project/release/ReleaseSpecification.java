package be.davidopdebeeck.rcaasapi.core.domain.project.release;

import be.davidopdebeeck.rcaasapi.core.domain.project.version.Version;

import java.time.LocalDate;
import java.util.Optional;

public interface ReleaseSpecification {

    Optional<Version> determineVersionAtDate(LocalDate date);

    Optional<Release> determineReleaseAtDate(LocalDate date);
}
