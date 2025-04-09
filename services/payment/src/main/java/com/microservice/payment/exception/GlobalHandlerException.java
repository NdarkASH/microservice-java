package com.microservice.payment.exception;

import com.microservice.payment.dto.AppResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandlerException {
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<AppResponse<String>> exceptionHandler(Exception e) {
        AppResponse<String> response = new AppResponse<>();
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setMessage(e.getMessage());
        response.setData(e.getMessage());

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(response);
    }

}
