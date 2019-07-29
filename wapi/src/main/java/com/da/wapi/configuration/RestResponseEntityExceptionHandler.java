package com.da.wapi.configuration;

import com.da.wapi.exception.*;
import com.da.wapi.model.ErrorJSON;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = AuthenticationError.class)
    protected ResponseEntity<Object> errorAuthentication(AuthenticationError ex, WebRequest request ){
        //TODO Add internationalization
        //TODO Define errorMessageCode

        ErrorJSON errorJSON = new ErrorJSON("User not autorized",
                 HttpStatus.FORBIDDEN.value(), "E001");

        return handleExceptionInternal(ex, errorJSON,
                new HttpHeaders(), HttpStatus.FORBIDDEN, request);
    }
    @ExceptionHandler(value = QueueError.class)
    protected ResponseEntity<Object> errorQueueing(QueueError ex, WebRequest request){
        ErrorJSON errorJSON = new ErrorJSON("Internal Error",
                HttpStatus.INTERNAL_SERVER_ERROR.value(), "E002");
        return handleExceptionInternal(ex, errorJSON,
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(value = EmptyFieldError.class)
    protected ResponseEntity<Object> requestWithEmptyFields(EmptyFieldError ex,  WebRequest request){
        ErrorJSON errorJSON = new ErrorJSON(ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(), "E003");
        return handleExceptionInternal(ex, errorJSON,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    @ExceptionHandler(value = PriceIsZeroOrNegativeError.class)
    protected ResponseEntity<Object> requestWithPriceZeroOrNegative(PriceIsZeroOrNegativeError ex,  WebRequest request){
        ErrorJSON errorJSON = new ErrorJSON(ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(), "E004");
        return handleExceptionInternal(ex, errorJSON,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
    @ExceptionHandler(value = WrongIdFormatError.class)
    protected ResponseEntity<Object> requestWithWrongIdFormat(WrongIdFormatError ex,  WebRequest request){
        ErrorJSON errorJSON = new ErrorJSON(ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(), "E005");
        return handleExceptionInternal(ex, errorJSON,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }


}
