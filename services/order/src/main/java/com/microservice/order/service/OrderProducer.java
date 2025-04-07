package com.microservice.order.service;

import com.microservice.order.dto.OrderConfirmation;

public interface OrderProducer {
    void sendOrderConfirmation(OrderConfirmation orderConfirmation);
}
