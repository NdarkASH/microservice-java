package com.microservice.order.utility;

import com.microservice.order.dto.OrderLineRequest;
import com.microservice.order.dto.OrderLineResponse;
import com.microservice.order.entity.Order;
import com.microservice.order.entity.OrderLine;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderLineModelUtility {

    public OrderLine toOrderLineModel(OrderLineRequest request, Order order) {
        OrderLine orderLine = new OrderLine();
        orderLine.setId(request.getId());
        orderLine.setProductId(request.getProductId());
        orderLine.setOrder(order);
        orderLine.setQuantity(request.getQuantity());
        return orderLine;
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return OrderLineResponse.builder()
                .id(orderLine.getId())
                .quantity(orderLine.getQuantity())
                .build();
    }
}
