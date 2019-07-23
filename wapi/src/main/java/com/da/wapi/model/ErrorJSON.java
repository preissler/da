package com.da.wapi.model;

public class ErrorJSON {
    private String message;
    private int statusCode;
    private String errorMessageCode;


    public ErrorJSON(String message, int statusCode, String errorMessageCode) {
        this.message = message;
        this.statusCode = statusCode;
        this.errorMessageCode = errorMessageCode;
    }

    public String getMessage() {
        return message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorMessageCode() {
        return errorMessageCode;
    }


}
