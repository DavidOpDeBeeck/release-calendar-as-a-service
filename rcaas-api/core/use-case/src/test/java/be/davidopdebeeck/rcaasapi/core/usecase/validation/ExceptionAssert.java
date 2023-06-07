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
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.SoftAssertionsProvider.ThrowingRunnable;

public class ExceptionAssert extends AbstractAssert<ExceptionAssert, Exception> {

    public static ExceptionAssert assertThat(Exception actual) {
        return new ExceptionAssert(actual);
    }

    public static ExceptionAssert assertThatThrownBy(ThrowingRunnable runnable) {
        try {
            runnable.run();
            return new ExceptionAssert(null);
        } catch (Exception exception) {
            return new ExceptionAssert(exception);
        }
    }

    public ExceptionAssert(Exception actual) {
        super(actual, ExceptionAssert.class);
    }

    public ValidationExceptionAssert isValidationException() {
        isNotNull();
        isInstanceOf(ValidationException.class);
        return new ValidationExceptionAssert((ValidationException) actual);
    }
}