package be.davidopdebeeck.rcaasapi.drivingadapter.exception;

import be.davidopdebeeck.rcaasapi.core.framework.validation.ValidationException;
import be.davidopdebeeck.rcaasapi.core.framework.validation.ValidationMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<List<ErrorMessage>> handleOtherExceptions(Exception exception) {
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

