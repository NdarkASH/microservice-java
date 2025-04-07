package com.microservice.order.service;

import com.microservice.order.dto.OrderLineRequest;
import com.microservice.order.dto.OrderLineResponse;

import java.util.List;

public interface OrderLineService {

    Integer saveOrderLine(OrderLineRequest request);

    List<OrderLineResponse> findAllOrderLineByOrderId(Integer orderId);
}
