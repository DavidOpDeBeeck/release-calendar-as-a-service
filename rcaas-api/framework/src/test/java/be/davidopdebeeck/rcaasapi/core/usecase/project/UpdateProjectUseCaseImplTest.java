package be.davidopdebeeck.rcaasapi.core.usecase.project;

import be.davidopdebeeck.rcaasapi.core.usecase.UseCaseTest;
import be.davidopdebeeck.rcaasapi.core.usecase.stubs.ProjectTestRepository;
import be.davidopdebeeck.rcaasapi.transferobject.project.UpdateProjectTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.ANOTHER_PROJECT_NAME;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.PROJECT_ID;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.PROJECT_ID_VALUE;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.anotherReleaseSpecificationTO;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.anotherSprintBasedReleaseSpecification;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.project;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.projectBuilder;
import static org.assertj.core.api.Assertions.assertThat;

@UseCaseTest
class UpdateProjectUseCaseImplTest {

    @Autowired
    private UpdateProjectUseCaseImpl useCase;
    @Autowired
    private ProjectTestRepository repository;

    @Test
    void updateProject() {
        repository.save(project());

        useCase.updateProject(PROJECT_ID_VALUE, new UpdateProjectTO.Builder()
            .withName(ANOTHER_PROJECT_NAME)
            .withSpecifications(List.of(anotherReleaseSpecificationTO()))
            .build());

        assertThat(repository.findBy(PROJECT_ID))
            .usingRecursiveComparison()
            .isEqualTo(Optional.of(projectBuilder()
                .withProjectId(PROJECT_ID)
                .withName(ANOTHER_PROJECT_NAME)
                .withSpecifications(List.of(anotherSprintBasedReleaseSpecification()))
                .build()));
    }
}