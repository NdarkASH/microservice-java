package com.microservice.order.service.impl;

import com.microservice.order.dto.OrderLineRequest;
import com.microservice.order.dto.OrderLineResponse;
import com.microservice.order.entity.Order;
import com.microservice.order.entity.OrderLine;
import com.microservice.order.repository.OrderLineRepository;
import com.microservice.order.service.OrderLineService;
import com.microservice.order.service.ServiceValidation;
import com.microservice.order.utility.OrderLineModelUtility;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineServiceImplement implements OrderLineService {
    private final OrderLineRepository orderLineRepository;
    @PersistenceContext
    private final EntityManager entityManager;
    private final ServiceValidation validation;


    @Override
    public Integer saveOrderLine(OrderLineRequest request) {
        validation.validate(request);
        Order findOrder = entityManager.find(Order.class, request.getOrderId());
        OrderLine orderLine = OrderLineModelUtility.toOrderLineModel(request, findOrder);
        OrderLine savedOrderLine = orderLineRepository.save(orderLine);
        return OrderLineModelUtility.toOrderLineResponse(savedOrderLine).getId();
    }

    @Override
    public List<OrderLineResponse> findAllOrderLineByOrderId(Integer orderId) {
        return orderLineRepository.findByOrderId(orderId)
                .stream()
                .map(OrderLineModelUtility::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}
