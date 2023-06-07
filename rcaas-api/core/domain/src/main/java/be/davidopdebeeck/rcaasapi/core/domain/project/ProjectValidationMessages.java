package be.davidopdebeeck.rcaasapi.core.domain.project;

import be.davidopdebeeck.rcaasapi.core.framework.validation.ValidationMessage;

public enum ProjectValidationMessages implements ValidationMessage {

    PROJECT_NAME_SHOULD_NOT_BE_NULL;

    @Override
    public String getMessage() {
        return name();
    }
}
