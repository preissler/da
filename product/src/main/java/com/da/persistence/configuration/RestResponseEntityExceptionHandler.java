package com.da.persistence.configuration;


import com.da.common.model.json.ErrorJSON;
import com.da.persistence.exception.TimeoutError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = TimeoutError.class)
    protected ResponseEntity<Object> errorTimeout(TimeoutError ex, WebRequest request ){
        ErrorJSON errorJSON = new ErrorJSON(ex.getMessage(),
                 HttpStatus.REQUEST_TIMEOUT.value(), "E001");

        return handleExceptionInternal(ex, errorJSON,
                new HttpHeaders(), HttpStatus.REQUEST_TIMEOUT, request);
    }

    @ExceptionHandler(value = InternalError.class)
    protected ResponseEntity<Object> errorInternalError(TimeoutError ex, WebRequest request ){
        ErrorJSON errorJSON = new ErrorJSON(ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "E002");

        return handleExceptionInternal(ex, errorJSON,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    @ExceptionHandler(value = NullPointerException.class)
    protected ResponseEntity<Object> nullpointerInternalError(NullPointerException ex, WebRequest request ){
        ErrorJSON errorJSON = new ErrorJSON("Internal Error",
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "E003");

        return handleExceptionInternal(ex, errorJSON,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }
    @ExceptionHandler(value = IllegalArgumentException.class)
    protected ResponseEntity<Object> nullpointerInternalError(IllegalArgumentException ex, WebRequest request ){
        ErrorJSON errorJSON = new ErrorJSON("Internal Error",
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "E004");

        return handleExceptionInternal(ex, errorJSON,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }





}
