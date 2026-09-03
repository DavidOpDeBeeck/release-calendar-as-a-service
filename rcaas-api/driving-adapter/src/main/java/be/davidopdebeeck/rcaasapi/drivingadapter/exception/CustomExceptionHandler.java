package be.davidopdebeeck.rcaasapi.drivingadapter.exception;

import be.davidopdebeeck.rcaasapi.core.framework.validation.ValidationException;
import be.davidopdebeeck.rcaasapi.core.framework.validation.ValidationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler
    public ResponseEntity<List<ErrorMessage>> handleOtherExceptions(Exception exception) {
        LOGGER.error("An unexpected error occurred", exception);
        return new ResponseEntity<>(List.of(new ErrorMessage("Something went wrong")), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<List<ErrorMessage>> handleValidationException(ValidationException exception) {
        List<ErrorMessage> errorMessages = exception.getMessages().stream()
            .map(ValidationMessage::getMessage)
            .map(ErrorMessage::new)
            .toList();

        return new ResponseEntity<>(errorMessages, BAD_REQUEST);
    }
}

