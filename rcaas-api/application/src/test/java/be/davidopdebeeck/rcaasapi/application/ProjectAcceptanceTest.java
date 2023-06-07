package be.davidopdebeeck.rcaasapi.application;

import be.davidopdebeeck.rcaasapi.transferobject.project.CreateProjectTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectIdTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.UpdateProjectTO;
import be.davidopdebeeck.rcaasapi.transferobject.project.calendar.CalendarTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.YearMonth;
import java.util.List;

import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.ANOTHER_PROJECT_NAME;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.PROJECT_NAME;
import static be.davidopdebeeck.rcaasapi.testconstant.ProjectTestConstants.releaseSpecificationTO;
import static java.lang.String.format;
import static java.time.format.DateTimeFormatter.ofPattern;
import static java.util.Collections.emptyList;
import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;
import static reactor.core.publisher.Mono.just;

@AcceptanceTest
class ProjectAcceptanceTest {

    private static final YearMonth JAN_2023 = YearMonth.of(2023, 1);

    @LocalServerPort
    private int port;
    @Autowired
    private WebTestClient client;

    @BeforeEach
    void setUp() {
        client.mutate()
            .baseUrl("http://localhost:" + port);
    }

    @Test
    void postProject() {
        String projectId = createProject(new CreateProjectTO.Builder()
            .withName(PROJECT_NAME)
            .build());

        assertThat(findProject(projectId))
            .usingRecursiveComparison()
            .isEqualTo(new ProjectTO.Builder()
                .withProjectId(projectId)
                .withName(PROJECT_NAME)
                .withSpecifications(emptyList())
                .build());
    }

    @Test
    void updateProject() {
        String projectId = createProject(new CreateProjectTO.Builder()
            .withName(PROJECT_NAME)
            .build());

        updateProject(projectId, new UpdateProjectTO.Builder()
            .withName(ANOTHER_PROJECT_NAME)
            .withSpecifications(List.of(releaseSpecificationTO()))
            .build());

        assertThat(findProject(projectId))
            .usingRecursiveComparison()
            .isEqualTo(new ProjectTO.Builder()
                .withProjectId(projectId)
                .withName(ANOTHER_PROJECT_NAME)
                .withSpecifications(List.of(releaseSpecificationTO()))
                .build());
    }

    @Test
    void getProjectCalendar() {
        String projectId = createProject(new CreateProjectTO.Builder()
            .withName(PROJECT_NAME)
            .build());

        assertThat(findProjectCalendar(projectId, JAN_2023))
            .satisfies(calendar -> {
                assertThat(calendar.getWeeks()).hasSize(6);
                assertThat(calendar.getWeeks()).allSatisfy(weekTO ->
                    assertThat(weekTO.getDays()).hasSize(7));
            });
    }

    private String createProject(CreateProjectTO createProjectTO) {
        return requireNonNull(client.post()
            .uri("/projects")
            .body(just(createProjectTO), CreateProjectTO.class)
            .exchange()
            .expectStatus().isCreated()
            .expectBody(ProjectIdTO.class)
            .returnResult()
            .getResponseBody())
            .getProjectId();
    }

    private void updateProject(String projectId, UpdateProjectTO updateProjectTO) {
        client.put()
            .uri(format("/projects/%s", projectId))
            .body(just(updateProjectTO), CreateProjectTO.class)
            .exchange()
            .expectStatus().isOk();
    }

    private ProjectTO findProject(String projectId) {
        return client.get()
            .uri(format("/projects/%s", projectId))
            .exchange()
            .expectStatus().isOk()
            .expectBody(ProjectTO.class)
            .returnResult()
            .getResponseBody();
    }

    private CalendarTO findProjectCalendar(String projectId, YearMonth yearMonth) {
        return client.get()
            .uri(format("/projects/%s/calendar?yearMonth=%s", projectId, yearMonth.format(ofPattern("yyyy-MM"))))
            .exchange()
            .expectStatus().isOk()
            .expectBody(CalendarTO.class)
            .returnResult()
            .getResponseBody();
    }
}
