package be.davidopdebeeck.rcaasapi.core.framework.validation;

import java.util.List;

import static java.util.Arrays.stream;
import static java.util.function.Predicate.not;

public class ValidationConstraints {

    public static void validateConstraints(ValidationCondition... conditions) {
        List<ValidationMessage> validationMessages = stream(conditions)
            .filter(not(ValidationCondition::isSatisfied))
            .map(ValidationCondition::getMessage)
            .toList();

        if (validationMessages.isEmpty()) {
            return;
        }

        throw new ValidationException(validationMessages);
    }
}
