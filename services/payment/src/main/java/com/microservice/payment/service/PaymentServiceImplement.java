package com.microservice.payment.service;

import com.microservice.payment.dto.PaymentRequest;
import com.microservice.payment.entity.Payment;
import com.microservice.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImplement implements PaymentService {

    private final ValidationService validation;
    private final PaymentRepository repository;

    @Override
    public Integer payment(PaymentRequest request) {
        validation.validate(request);
        Payment payment = insertPayment(request);
        return payment.getId();
    }

    private Payment insertPayment(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setAmount(request.getAmount());
        payment.setOrderId(request.getOrderId());
        payment.setAmount(request.getAmount());
        payment.setPaymentMethod(request.getMethod());
        return repository.save(payment);
    }

}
