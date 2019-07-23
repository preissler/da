package com.da.wapi.configuration;

public class AuthenticationError extends RuntimeException {

    public AuthenticationError(String message) {
        super(message);
    }
}