package be.davidopdebeeck.rcaasapi.core.usecase.project;

import app.dodb.smd.test.SMDTestExtension;
import be.davidopdebeeck.rcaasapi.core.usecase.UseCaseTest;
import be.davidopdebeeck.rcaasapi.core.usecase.stubs.ProjectTestRepository;
import be.davidopdebeeck.rcaasapi.drivingport.project.FindProjectByIdQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.PROJECT_ID_VALUE;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.project;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.projectTO;
import static org.assertj.core.api.Assertions.assertThat;

@UseCaseTest
class FindProjectByIdUseCaseTest {

    @Autowired
    private ProjectTestRepository repository;
    @Autowired
    private SMDTestExtension smd;

    @Test
    void findProjectById_whenProjectExists() {
        repository.save(project());

        assertThat(smd.send(new FindProjectByIdQuery(PROJECT_ID_VALUE)))
            .usingRecursiveComparison()
            .isEqualTo(Optional.of(projectTO()));
    }

    @Test
    void findProjectById_whenProjectDoesNotExist() {
        assertThat(smd.send(new FindProjectByIdQuery(PROJECT_ID_VALUE)))
            .isEmpty();
    }
}