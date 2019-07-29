package com.da.wapi.exception;

public class AuthenticationError extends RuntimeException {

    public AuthenticationError(String message) {
        super(message);
    }
}