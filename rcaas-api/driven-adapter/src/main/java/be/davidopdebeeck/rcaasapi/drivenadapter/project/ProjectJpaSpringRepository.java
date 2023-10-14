package be.davidopdebeeck.rcaasapi.drivenadapter.project;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectJpaSpringRepository extends JpaRepository<ProjectJpaEntity, String> {
}
