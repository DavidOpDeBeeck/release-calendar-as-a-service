package be.davidopdebeeck.rcaasapi.core.domain.project;

import be.davidopdebeeck.rcaasapi.core.framework.validation.ValidationMessage;

public enum ProjectValidationMessages implements ValidationMessage {

    PROJECT_NAME_SHOULD_NOT_BE_NULL,
    RESCHEDULING_FROM_DATE_SHOULD_BE_A_RELEASE_DATE,
    FROM_DATE_SHOULD_BE_BEFORE_TO_DATE;

    @Override
    public String getMessage() {
        return name();
    }
}
