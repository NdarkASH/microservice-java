package com.microservice.product.exception;

public class ProductPurchaseNotFound extends RuntimeException {
    public ProductPurchaseNotFound(String message) {
        super(message);
    }
}
