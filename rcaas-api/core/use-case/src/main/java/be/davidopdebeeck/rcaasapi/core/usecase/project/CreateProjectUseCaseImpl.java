package be.davidopdebeeck.rcaasapi.core.usecase.project;

import be.davidopdebeeck.rcaasapi.core.domain.project.Project;
import be.davidopdebeeck.rcaasapi.core.domain.project.ProjectId;
import be.davidopdebeeck.rcaasapi.drivenport.ProjectRepository;
import be.davidopdebeeck.rcaasapi.drivingport.project.CreateProjectUseCase;
import be.davidopdebeeck.rcaasapi.transferobject.project.CreateProjectTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectIdTO;
import org.springframework.stereotype.Component;

import static java.util.Collections.emptyList;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

@Component
public class CreateProjectUseCaseImpl implements CreateProjectUseCase {

    private final ProjectRepository repository;

    public CreateProjectUseCaseImpl(ProjectRepository repository) {
        this.repository = repository;
    }

    @Override
    public ProjectIdTO createProject(CreateProjectTO createProjectTO) {
        ProjectId projectId = createProjectId(createProjectTO);

        repository.save(new Project.Builder()
            .withProjectId(projectId)
            .withName(createProjectTO.getName().orElse(null))
            .withSpecifications(emptyList())
            .build());

        return new ProjectIdTO.Builder()
            .withProjectId(projectId.getValue())
            .build();
    }

    private ProjectId createProjectId(CreateProjectTO createProjectTO) {
        return createProjectTO.getName()
            .map(name -> name.chars()
                .filter(Character::isAlphabetic)
                .map(Character::toLowerCase)
                .mapToObj(Character::toChars)
                .map(String::new)
                .reduce("", String::concat)
                .concat("-")
                .concat(randomAlphabetic(5))
                .concat("-")
                .concat(randomAlphabetic(5)))
            .map(ProjectId::projectId)
            .orElse(null);
    }
}
