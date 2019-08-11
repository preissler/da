package com.da.persistence.exception;

public class TimeoutError extends RuntimeException {
    public TimeoutError(String message) {
        super(message);
    }
}
