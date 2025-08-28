package com.example.api.exception.handler;

import com.example.api.exception.CustomerNotFoundException;
import com.example.api.exception.ExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd - HH:mm:ss");

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetails> handleGlobalException(Exception ex, WebRequest request) {
        ExceptionDetails exceptionDetails = new ExceptionDetails();
        exceptionDetails.setTimestamp(LocalDateTime.now().format(dateTimeFormatter));
        exceptionDetails.setStatus(HttpStatus.BAD_REQUEST);
        exceptionDetails.setMessage("An unexpected error occurred");
        exceptionDetails.setDetails(ex.getMessage());
        exceptionDetails.setDeveloperMessage(ex.getClass().getName());
        exceptionDetails.setPath(request.getDescription(false));

        return new ResponseEntity<>(exceptionDetails, HttpStatus.BAD_REQUEST);
    }
}
