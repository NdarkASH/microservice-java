package com.microservice.payment.controller;

import com.microservice.payment.dto.AppResponse;
import com.microservice.payment.dto.PaymentRequest;
import com.microservice.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AppResponse<Integer> createPayment(@RequestBody PaymentRequest request) {
        Integer response = service.payment(request);

        return AppResponse.<Integer>builder()
                .code(HttpStatus.CREATED.value())
                .message("Payment created")
                .data(response)
                .build();
    }
}
