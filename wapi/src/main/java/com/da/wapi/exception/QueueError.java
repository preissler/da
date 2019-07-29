package com.da.wapi.exception;

public class QueueError extends RuntimeException{
    public QueueError(String message) {
        super(message);
    }
}
