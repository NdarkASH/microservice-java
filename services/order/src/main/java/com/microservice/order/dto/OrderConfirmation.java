package com.microservice.order.dto;

import com.microservice.order.entity.PaymentMethod;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderConfirmation {

    private String orderReference;

    private String totalAmount;

    private PaymentMethod paymentMethod;

    private CustomerResponse customerResponse;

    private List<PurchaseResponse> purchaseResponses;
}
