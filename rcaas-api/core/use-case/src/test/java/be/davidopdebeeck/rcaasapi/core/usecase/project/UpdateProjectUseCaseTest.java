package be.davidopdebeeck.rcaasapi.core.usecase.project;

import app.dodb.smd.test.SMDTestExtension;
import be.davidopdebeeck.rcaasapi.core.usecase.UseCaseTest;
import be.davidopdebeeck.rcaasapi.core.usecase.stubs.ProjectTestRepository;
import be.davidopdebeeck.rcaasapi.drivingport.project.UpdateProjectCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static be.davidopdebeeck.rcaasapi.core.domain.project.ProjectValidationMessages.PROJECT_NAME_SHOULD_NOT_BE_NULL;
import static be.davidopdebeeck.rcaasapi.core.usecase.validation.ExceptionAssert.assertThatThrownBy;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.ANOTHER_PROJECT_NAME;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.PROJECT_ID;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.PROJECT_ID_VALUE;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.anotherReleaseSpecificationTO;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.anotherSprintBasedReleaseSpecification;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.project;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.projectBuilder;
import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

@UseCaseTest
class UpdateProjectUseCaseTest {

    @Autowired
    private ProjectTestRepository repository;
    @Autowired
    private SMDTestExtension smd;

    @Test
    void updateProject() {
        repository.save(project());

        smd.send(new UpdateProjectCommand(PROJECT_ID_VALUE, ANOTHER_PROJECT_NAME, List.of(anotherReleaseSpecificationTO())));

        assertThat(repository.findBy(PROJECT_ID))
            .usingRecursiveComparison()
            .isEqualTo(Optional.of(projectBuilder()
                .withProjectId(PROJECT_ID)
                .withName(ANOTHER_PROJECT_NAME)
                .withSpecifications(List.of(anotherSprintBasedReleaseSpecification()))
                .build()));
    }


    @Test
    void updateProject_withoutName() {
        repository.save(project());

        assertThatThrownBy(() -> smd.send(new UpdateProjectCommand(PROJECT_ID_VALUE, null, emptyList())))
            .isValidationException()
            .containsMessage(PROJECT_NAME_SHOULD_NOT_BE_NULL);
    }
}