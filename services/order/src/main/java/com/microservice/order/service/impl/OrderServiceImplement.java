package com.microservice.order.service.impl;

import com.microservice.order.dto.OrderRequest;
import com.microservice.order.dto.OrderResponse;
import com.microservice.order.entity.Order;
import com.microservice.order.repository.OrderRepository;
import com.microservice.order.service.OrderService;
import com.microservice.order.service.ServiceValidation;
import com.microservice.order.utility.OrderModelUtility;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class OrderServiceImplement implements OrderService {
    private final OrderRepository orderRepository;
    private final ServiceValidation validation;
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public Integer addOrder(OrderRequest request) {
        validation.validate(request);
        Order order = OrderModelUtility.toOrderModel(request);
        Order savedOrder = orderRepository.save(order);
        return OrderModelUtility.toOrderResponse(savedOrder).getId();
    }

    @Override
    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(OrderModelUtility::toOrderResponse)
                .collect(Collectors.toList());
    }

    @Override
    public OrderResponse findOrderById(Integer id) {
       return orderRepository.findById(id)
               .map(OrderModelUtility::toOrderResponse)
               .orElseThrow(()-> new EntityNotFoundException("Order with id: " + id + " not found"));
    }
}
