package com.da.wapi.configuration;

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
}
