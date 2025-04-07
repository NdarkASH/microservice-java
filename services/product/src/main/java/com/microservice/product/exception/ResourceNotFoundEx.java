package com.microservice.product.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ResourceNotFoundEx extends RuntimeException {
    public ResourceNotFoundEx(String message) {
        super(message);
    }
}
