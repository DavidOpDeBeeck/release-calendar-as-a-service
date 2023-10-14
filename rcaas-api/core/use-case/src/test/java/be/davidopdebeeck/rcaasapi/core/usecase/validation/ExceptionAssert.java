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