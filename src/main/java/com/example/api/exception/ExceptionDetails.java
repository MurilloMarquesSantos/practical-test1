package com.example.api.exception;

import org.springframework.http.HttpStatus;

public class ExceptionDetails {

    private String timestamp;

    private String message;

    private String details;

    private String developerMessage;

    private HttpStatus status;

    private String path;

    public String getTimestamp() {
        return timestamp;
    }

    public ExceptionDetails setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ExceptionDetails setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDetails() {
        return details;
    }

    public ExceptionDetails setDetails(String details) {
        this.details = details;
        return this;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public ExceptionDetails setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
        return this;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public ExceptionDetails setStatus(HttpStatus status) {
        this.status = status;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ExceptionDetails setPath(String path) {
        this.path = path;
        return this;
    }

}
