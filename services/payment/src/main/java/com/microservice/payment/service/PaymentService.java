package com.microservice.payment.service;

import com.microservice.payment.dto.PaymentRequest;

public interface PaymentService {
    Integer payment(PaymentRequest request);
}
