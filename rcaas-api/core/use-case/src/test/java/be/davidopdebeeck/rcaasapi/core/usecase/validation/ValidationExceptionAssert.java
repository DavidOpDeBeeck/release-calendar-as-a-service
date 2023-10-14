package be.davidopdebeeck.rcaasapi.core.usecase.validation;

import be.davidopdebeeck.rcaasapi.core.framework.validation.ValidationException;
import be.davidopdebeeck.rcaasapi.core.framework.validation.ValidationMessage;
import org.assertj.core.api.AbstractAssert;

import java.util.List;

public class ValidationExceptionAssert extends AbstractAssert<ValidationExceptionAssert, ValidationException> {

    public static ValidationExceptionAssert assertThat(ValidationException actual) {
        return new ValidationExceptionAssert(actual);
    }

    public ValidationExceptionAssert(ValidationException actual) {
        super(actual, ValidationExceptionAssert.class);
    }

    public ValidationExceptionAssert containsMessage(ValidationMessage message) {
        isNotNull();
        List<ValidationMessage> messages = actual.getMessages();
        if (!messages.contains(message)) {
            failWithMessage("Expected validation exception message to contain <%s> but was <%s>", message, messages);
        }
        return this;
    }
}