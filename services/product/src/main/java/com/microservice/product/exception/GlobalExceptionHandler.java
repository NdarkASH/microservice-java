package com.microservice.product.exception;

import com.microservice.product.dto.AppResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundEx.class)
    public ResponseEntity<AppResponse<String>> productException(Exception ex) {
        AppResponse<String> appResponse = new AppResponse<>();
        appResponse.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        appResponse.setMessage(ex.getMessage());
        appResponse.setData(null);
      return ResponseEntity
              .status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body(appResponse);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<AppResponse<String>> databaseException(ConstraintViolationException ex) {
        AppResponse<String> appResponse = new AppResponse<>();
        appResponse.setMessage(ex.getMessage());
        return ResponseEntity.badRequest().body(appResponse);
    }

    @ExceptionHandler(ProductPurchaseNotFound.class)
    public ResponseEntity<AppResponse<String>> productPurchaseNotFound(Exception ex) {
        AppResponse<String> appResponse = new AppResponse<>();
        appResponse.setMessage(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(appResponse);
    }

}
