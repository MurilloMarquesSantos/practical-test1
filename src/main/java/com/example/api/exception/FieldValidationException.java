package com.example.api.exception;

public class FieldValidationException extends RuntimeException {
    public FieldValidationException(String message) {
        super(message);
    }

}
