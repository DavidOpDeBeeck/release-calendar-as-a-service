package be.davidopdebeeck.rcaasapi.drivenadapter.project;

import be.davidopdebeeck.rcaasapi.transferobject.project.ProjectTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.io.IOException;

@Converter
public class ProjectTOJsonConverter implements AttributeConverter<ProjectTO, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .registerModule(new Jdk8Module())
        .registerModule(new SimpleModule())
        .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    @Override
    public String convertToDatabaseColumn(ProjectTO project) {
        try {
            return objectMapper.writeValueAsString(project);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public ProjectTO convertToEntityAttribute(String value) {
        try {
            return objectMapper.readValue(value, ProjectTO.class);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
