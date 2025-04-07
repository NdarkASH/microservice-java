package com.microservice.order.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class OrderResponse {

    private Integer id;

    private String reference;

    private BigDecimal amount;

    private PaymentMethod paymentMethod;

    private String customerId;
}
