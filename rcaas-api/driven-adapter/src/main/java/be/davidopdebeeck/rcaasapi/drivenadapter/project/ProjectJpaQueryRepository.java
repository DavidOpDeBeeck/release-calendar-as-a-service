package be.davidopdebeeck.rcaasapi.drivenadapter.project;

import be.davidopdebeeck.rcaasapi.core.domain.project.ProjectId;
import be.davidopdebeeck.rcaasapi.drivenport.ProjectQueryRepository;
import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectTO;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ProjectJpaQueryRepository implements ProjectQueryRepository {

    private final ProjectJpaSpringRepository repository;

    public ProjectJpaQueryRepository(ProjectJpaSpringRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<ProjectTO> queryBy(ProjectId projectId) {
        return repository.findById(projectId.getValue())
            .map(ProjectJpaEntity::getProjectTO);
    }
}
