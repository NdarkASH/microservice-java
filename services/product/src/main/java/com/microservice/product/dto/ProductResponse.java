package com.microservice.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Integer id;

    private String name;

    private String description;

    private Double quantity;

    private BigDecimal price;

    private CategoryResponse category;

}
