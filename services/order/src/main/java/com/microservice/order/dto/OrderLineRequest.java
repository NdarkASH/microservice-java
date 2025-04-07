package com.microservice.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineRequest {
    private Integer id;

    private Integer orderId;

    private Integer productId;

    private Double quantity;
}
