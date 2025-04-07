package com.microservice.product.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductPurchaseRequest {
    @NotNull(message = "product is mandatory")
    private int productId;

    @NotNull(message = "quantity is mandatory")
    private Integer quantity;

}
