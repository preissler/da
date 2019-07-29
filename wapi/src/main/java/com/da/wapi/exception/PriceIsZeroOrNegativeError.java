package com.da.wapi.exception;

public class PriceIsZeroOrNegativeError extends RuntimeException{
    public PriceIsZeroOrNegativeError(String message) {
        super(message);
    }
}
