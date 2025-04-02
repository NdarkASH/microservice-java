package com.microservice.product.exception;

import com.microservice.product.dto.AppResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<AppResponse<Void>> globalExceptionHandler(Exception ex) {
        AppResponse<Void> appResponse = new AppResponse<>();
        appResponse.setMessage(ex.getMessage());
        return new ResponseEntity<>(appResponse, HttpStatus.NOT_FOUND);
    }
}
