package com.microservice.order.dto;

import com.microservice.order.entity.PaymentMethod;
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

    private BigDecimal amount;

    private PaymentMethod paymentMethod;

    private Integer orderId;

    private String orderReference;

    private CustomerResponse customer;
}
