package com.clarke.rest.beans;

import java.time.LocalDateTime;


public class ErrorDetails {
    private LocalDateTime date;
    private String message;
    private String details;

    public ErrorDetails(LocalDateTime date, String message, String details) {
        this.date = date;
        this.message = message;
        this.details = details;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "ErrorDetails{" + "date=" + date + ", message=" + message + ", details=" + details + '}';
    }
}
