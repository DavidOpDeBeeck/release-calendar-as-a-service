package be.davidopdebeeck.rcaasapi.core.usecase.project;

import app.dodb.smd.test.SMDTestExtension;
import be.davidopdebeeck.rcaasapi.core.domain.project.Project;
import be.davidopdebeeck.rcaasapi.core.domain.project.ProjectId;
import be.davidopdebeeck.rcaasapi.core.usecase.UseCaseTest;
import be.davidopdebeeck.rcaasapi.core.usecase.stubs.ProjectTestRepository;
import be.davidopdebeeck.rcaasapi.drivingport.project.CreateProjectCommand;
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
class CreateProjectUseCaseTest {

    @Autowired
    private ProjectTestRepository repository;
    @Autowired
    private SMDTestExtension smd;

    @Test
    void createProject() {
        ProjectIdTO projectIdTO = smd.send(new CreateProjectCommand(PROJECT_NAME));

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
        assertThatThrownBy(() -> smd.send(new CreateProjectCommand(null)))
            .isValidationException()
            .containsMessage(PROJECT_NAME_SHOULD_NOT_BE_NULL);
    }
}