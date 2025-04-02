package com.microservice.product.dto;

import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private String name;

    private String description;

    @Positive(message = "Avaible must be positive")
    private Double quantity;

    @Positive(message = "price must be above 0")
    private BigDecimal price;

    private Integer categoryId;

}
