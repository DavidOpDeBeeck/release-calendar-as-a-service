package be.davidopdebeeck.rcaasapi.core.usecase.project;

import be.davidopdebeeck.rcaasapi.core.usecase.UseCaseTest;
import be.davidopdebeeck.rcaasapi.core.usecase.stubs.ProjectTestRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.PROJECT_ID_VALUE;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.project;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.projectTO;
import static org.assertj.core.api.Assertions.assertThat;

@UseCaseTest
class FindProjectByIdUseCaseImplTest {

    @Autowired
    private FindProjectByIdUseCaseImpl useCase;
    @Autowired
    private ProjectTestRepository repository;

    @Test
    void findProjectById_whenProjectExists() {
        repository.save(project());

        assertThat(useCase.findProjectById(PROJECT_ID_VALUE))
            .usingRecursiveComparison()
            .isEqualTo(Optional.of(projectTO()));
    }

    @Test
    void findProjectById_whenProjectDoesNotExist() {
        assertThat(useCase.findProjectById(PROJECT_ID_VALUE))
            .isEmpty();
    }
}