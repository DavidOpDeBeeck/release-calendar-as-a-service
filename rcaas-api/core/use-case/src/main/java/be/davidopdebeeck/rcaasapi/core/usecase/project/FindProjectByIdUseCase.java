package be.davidopdebeeck.rcaasapi.core.usecase.project;

import app.dodb.smd.api.query.QueryHandler;
import be.davidopdebeeck.rcaasapi.drivenport.ProjectQueryRepository;
import be.davidopdebeeck.rcaasapi.drivingport.project.FindProjectByIdQuery;
import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectTO;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static be.davidopdebeeck.rcaasapi.core.domain.project.ProjectId.projectId;

@Component
public class FindProjectByIdUseCase {

    private final ProjectQueryRepository queryRepository;

    public FindProjectByIdUseCase(ProjectQueryRepository queryRepository) {
        this.queryRepository = queryRepository;
    }

    @QueryHandler
    public Optional<ProjectTO> handle(FindProjectByIdQuery query) {
        return queryRepository.queryBy(projectId(query.projectId()));
    }
}
