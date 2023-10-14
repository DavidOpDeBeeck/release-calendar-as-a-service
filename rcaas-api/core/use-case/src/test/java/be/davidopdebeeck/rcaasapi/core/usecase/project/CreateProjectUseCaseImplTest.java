package be.davidopdebeeck.rcaasapi.core.usecase.project;

import be.davidopdebeeck.rcaasapi.core.domain.project.Project;
import be.davidopdebeeck.rcaasapi.core.domain.project.ProjectId;
import be.davidopdebeeck.rcaasapi.core.usecase.UseCaseTest;
import be.davidopdebeeck.rcaasapi.core.usecase.stubs.ProjectTestRepository;
import be.davidopdebeeck.rcaasapi.transferobject.project.CreateProjectTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectIdTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static be.davidopdebeeck.rcaasapi.core.domain.project.ProjectId.projectId;
import static be.davidopdebeeck.rcaasapi.core.domain.project.ProjectValidationMessages.PROJECT_NAME_SHOULD_NOT_BE_NULL;
import static be.davidopdebeeck.rcaasapi.core.usecase.validation.ExceptionAssert.assertThatThrownBy;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.PROJECT_NAME;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

@UseCaseTest
class CreateProjectUseCaseImplTest {

    @Autowired
    private CreateProjectUseCaseImpl useCase;
    @Autowired
    private ProjectTestRepository repository;

    @Test
    void createProject() {
        ProjectIdTO projectIdTO = useCase.createProject(new CreateProjectTO.Builder()
            .withName(PROJECT_NAME)
            .build());

        ProjectId projectId = projectId(projectIdTO.getProjectId());
        assertThat(repository.findBy(projectId))
            .usingRecursiveComparison()
            .isEqualTo(Optional.of(new Project.Builder()
                .withProjectId(projectId)
                .withName(PROJECT_NAME)
                .withSpecifications(emptyList())
                .build()));
    }

    @Test
    void createProject_withoutName() {
        CreateProjectTO projectWithoutName = new CreateProjectTO.Builder()
            .withName(null)
            .build();

        assertThatThrownBy(() -> useCase.createProject(projectWithoutName))
            .isValidationException()
            .containsMessage(PROJECT_NAME_SHOULD_NOT_BE_NULL);
    }
}