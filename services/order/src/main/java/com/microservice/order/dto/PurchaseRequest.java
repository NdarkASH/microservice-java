package com.microservice.order.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class PurchaseRequest {

    @NotNull(message = "Product is mandatory")
    private Integer productId;

    @Positive(message = "Quantity is mandatory")
    private Double quantity;
}
