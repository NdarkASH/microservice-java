package com.microservice.order.utility;

import com.microservice.order.dto.OrderRequest;
import com.microservice.order.dto.OrderResponse;
import com.microservice.order.entity.Order;
import lombok.experimental.UtilityClass;


@UtilityClass
public class OrderModelUtility {

    public Order toOrderModel(OrderRequest request){
        Order order = new Order();
        order.setCustomerId(request.getCustomerId());
        order.setReference(request.getReference());
        order.setPaymentMethod(request.getPaymentMethod());
        order.setCustomerId(request.getCustomerId());
        return order;
    }

    public OrderResponse toOrderResponse(Order order){
        return OrderResponse.builder()
                .id(order.getId())
                .customerId(order.getCustomerId())
                .reference(order.getReference())
                .paymentMethod(order.getPaymentMethod())
                .amount(order.getTotalAmount())
                .build();
    }
}
