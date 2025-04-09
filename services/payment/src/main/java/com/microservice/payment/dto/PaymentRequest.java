package com.microservice.payment.dto;

import com.microservice.payment.entity.Customer;
import com.microservice.payment.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentRequest {
    private Integer id;

    private BigDecimal amount;

    private PaymentMethod method;

    private Integer orderId;

    private String orderReference;

    private Customer customer;
}
