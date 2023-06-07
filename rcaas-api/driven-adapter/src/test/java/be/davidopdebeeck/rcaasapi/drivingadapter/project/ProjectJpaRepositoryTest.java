package be.davidopdebeeck.rcaasapi.drivingadapter.project;

import be.davidopdebeeck.rcaasapi.drivenadapter.project.ProjectJpaEntity;
import be.davidopdebeeck.rcaasapi.drivenadapter.project.ProjectJpaRepository;
import be.davidopdebeeck.rcaasapi.drivingadapter.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.ANOTHER_PROJECT_ID_VALUE;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.PROJECT_ID;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.PROJECT_ID_VALUE;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.anotherProjectTO;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.project;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.projectTO;
import static java.util.Optional.of;
import static org.assertj.core.api.Assertions.assertThat;

@IntegrationTest
class ProjectJpaRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ProjectJpaRepository repository;

    @Test
    void findBy_whenProjectExists_thenReturnProject() {
        entityManager.persist(projectJpaEntity());
        entityManager.persist(anotherProjectJpaEntity());
        entityManager.flush();

        assertThat(repository.findBy(PROJECT_ID))
            .usingRecursiveComparison()
            .isEqualTo(of(project()));
    }

    @Test
    void findBy_whenProjectDoesNotExists_thenReturnEmpty() {
        assertThat(repository.findBy(PROJECT_ID))
            .isEmpty();
    }

    @Test
    void save() {
        repository.save(project());

        assertThat(entityManager.find(ProjectJpaEntity.class, PROJECT_ID_VALUE))
            .usingRecursiveComparison()
            .isEqualTo(projectJpaEntity());
    }

    private static ProjectJpaEntity projectJpaEntity() {
        return new ProjectJpaEntity.Builder()
            .withProjectId(PROJECT_ID_VALUE)
            .withProject(projectTO())
            .build();
    }

    private static ProjectJpaEntity anotherProjectJpaEntity() {
        return new ProjectJpaEntity.Builder()
            .withProjectId(ANOTHER_PROJECT_ID_VALUE)
            .withProject(anotherProjectTO())
            .build();
    }
}