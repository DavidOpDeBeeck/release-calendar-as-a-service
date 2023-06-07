/*-
 * #%L
 * Vlaams Handhavingsplatform
 * %%
 * Copyright (C) 2023 Vlaamse Overheid
 * %%
 * Licensed under the EUPL, Version 1.2 or – as soon they will be
 * approved by the European Commission - subsequent versions of the
 * EUPL (the "Licence");
 *
 * You may not use this work except in compliance with the Licence.
 * You may obtain a copy of the Licence at:
 *
 * https://joinup.ec.europa.eu/collection/eupl/eupl-text-eupl-12
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the Licence is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the Licence for the specific language governing permissions and
 * limitations under the Licence.
 * #L%
 */

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