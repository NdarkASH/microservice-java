package com.microservice.order.service;

import com.microservice.order.dto.OrderRequest;
import com.microservice.order.dto.OrderResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface OrderService {

    Integer addOrder(OrderRequest request);

    List<OrderResponse> findAllOrders();

    OrderResponse findOrderById(Integer id);
}
