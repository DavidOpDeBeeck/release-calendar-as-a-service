package be.davidopdebeeck.rcaasapi.core.usecase.project;

import app.dodb.smd.api.command.CommandHandler;
import app.dodb.smd.api.metadata.Metadata;
import be.davidopdebeeck.rcaasapi.core.domain.project.Project;
import be.davidopdebeeck.rcaasapi.core.domain.project.ProjectId;
import be.davidopdebeeck.rcaasapi.drivenport.ProjectRepository;
import be.davidopdebeeck.rcaasapi.drivingport.project.CreateProjectCommand;
import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectIdTO;
import org.springframework.stereotype.Component;

import static java.util.Collections.emptyList;
import static org.apache.commons.lang3.RandomStringUtils.secure;

@Component
public class CreateProjectUseCase {

    private final ProjectRepository repository;

    public CreateProjectUseCase(ProjectRepository repository) {
        this.repository = repository;
    }

    @CommandHandler
    public ProjectIdTO handle(CreateProjectCommand command, Metadata metadata) {
        ProjectId projectId = createProjectId(command);

        repository.save(new Project.Builder()
            .withProjectId(projectId)
            .withName(command.getName().orElse(null))
            .withSpecifications(emptyList())
            .build());

        return new ProjectIdTO.Builder()
            .withProjectId(projectId.getValue())
            .build();
    }

    private ProjectId createProjectId(CreateProjectCommand command) {
        return command.getName()
            .map(name -> name.chars()
                .filter(Character::isAlphabetic)
                .map(Character::toLowerCase)
                .mapToObj(Character::toChars)
                .map(String::new)
                .reduce("", String::concat)
                .concat("-")
                .concat(secure().nextAlphabetic(5))
                .concat("-")
                .concat(secure().nextAlphabetic(5)))
            .map(ProjectId::projectId)
            .orElse(null);
    }
}
