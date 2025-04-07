package com.microservice.order.service.impl;

import com.microservice.order.dto.OrderConfirmation;
import com.microservice.order.service.OrderProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import static org.springframework.kafka.support.KafkaHeaders.TOPIC;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderConfirmationImplement implements OrderProducer {

    private final KafkaTemplate<String, OrderConfirmation> kafkaTemplate;

    @Override
    public void sendOrderConfirmation(OrderConfirmation orderConfirmation) {
        log.info("Sending order confirmation: {}", orderConfirmation);
        Message<OrderConfirmation> message = MessageBuilder
                .withPayload(orderConfirmation)
                .setHeader(TOPIC, orderConfirmation)
                .build();

        kafkaTemplate.send(message);
    }
}
