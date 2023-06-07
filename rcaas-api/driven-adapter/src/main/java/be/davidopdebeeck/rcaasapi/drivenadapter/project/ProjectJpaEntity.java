package be.davidopdebeeck.rcaasapi.drivenadapter.project;

import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectTO;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import static java.util.Objects.requireNonNull;

@Entity
@Table(name = "calendar")
public class ProjectJpaEntity {

    @Id
    private String projectId;
    @Lob
    @Convert(converter = ProjectTOJsonConverter.class)
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
