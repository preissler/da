package com.da.wapi.exception;

public class EmptyFieldError extends RuntimeException{
    public EmptyFieldError(String message) {
        super(message);
    }
}
