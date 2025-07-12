package be.davidopdebeeck.rcaasapi.drivenadapter.project;

import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.JdbcTypeCode;

import static java.util.Objects.requireNonNull;
import static org.hibernate.type.SqlTypes.JSON;

@Entity
@Table(name = "projects")
public class ProjectJpaEntity {

    @Id
    private String projectId;
    @Column(name = "data")
    @JdbcTypeCode(JSON)
    private ProjectTO projectTO;

    private ProjectJpaEntity() {
    }

    private ProjectJpaEntity(Builder builder) {
        projectId = requireNonNull(builder.projectId);
        projectTO = requireNonNull(builder.projectTO);
    }

    public String getProjectId() {
        return projectId;
    }

    public ProjectTO getProjectTO() {
        return projectTO;
    }

    public static final class Builder {

        private String projectId;
        private ProjectTO projectTO;

        public Builder withProjectId(String calendarId) {
            this.projectId = calendarId;
            return this;
        }

        public Builder withProject(ProjectTO projectTO) {
            this.projectTO = projectTO;
            return this;
        }

        public ProjectJpaEntity build() {
            return new ProjectJpaEntity(this);
        }
    }
}
