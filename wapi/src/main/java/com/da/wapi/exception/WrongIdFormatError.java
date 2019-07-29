package com.da.wapi.exception;

public class WrongIdFormatError extends RuntimeException{
    public WrongIdFormatError(String message) {
        super(message);
    }
}
