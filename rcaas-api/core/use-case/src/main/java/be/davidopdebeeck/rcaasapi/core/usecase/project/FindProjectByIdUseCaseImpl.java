package be.davidopdebeeck.rcaasapi.core.usecase.project;

import be.davidopdebeeck.rcaasapi.drivenport.ProjectQueryRepository;
import be.davidopdebeeck.rcaasapi.drivingport.project.FindProjectByIdUseCase;
import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static be.davidopdebeeck.rcaasapi.core.domain.project.ProjectId.projectId;

@Component
public class FindProjectByIdUseCaseImpl implements FindProjectByIdUseCase {

    private final ProjectQueryRepository queryRepository;

    public FindProjectByIdUseCaseImpl(ProjectQueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    @Override
    public Optional<ProjectTO> findProjectById(String projectId) {
        return queryRepository.queryBy(projectId(projectId));
    }
}
