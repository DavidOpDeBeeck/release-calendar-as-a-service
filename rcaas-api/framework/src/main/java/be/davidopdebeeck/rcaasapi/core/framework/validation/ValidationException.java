package be.davidopdebeeck.rcaasapi.core.framework.validation;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static org.apache.commons.lang3.StringUtils.join;

public class ValidationException extends RuntimeException {

    private final List<ValidationMessage> messages;

    public ValidationException(List<ValidationMessage> messages) {
        super(join(messages));
        this.messages = requireNonNull(messages);
    }

    public List<ValidationMessage> getMessages() {
        return messages;
    }
}
