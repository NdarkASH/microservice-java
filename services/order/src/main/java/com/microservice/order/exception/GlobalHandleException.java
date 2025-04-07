package com.microservice.order.exception;

import com.microservice.order.dto.AppResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalHandleException {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<AppResponse<String>> productGotException(BusinessException e) {
        AppResponse<String> appResponse = new AppResponse<>();
        appResponse.setCode(HttpStatus.BAD_REQUEST.value());
        appResponse.setMessage(e.getMessage());
        appResponse.setData(null);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(appResponse);
    }

}
