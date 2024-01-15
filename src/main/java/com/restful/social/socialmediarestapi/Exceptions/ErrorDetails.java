package com.restful.social.socialmediarestapi.Exceptions;

import java.time.LocalDateTime;

public class ErrorDetails {
    private int status;
    private LocalDateTime timestamp;
    private String error;
    private String message;
    private String path;

    public String getError() {
        return error;
    }

    public ErrorDetails(int status, LocalDateTime timestamp, String error, String message, String path) {
        this.status = status;
        this.timestamp = timestamp;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

}
