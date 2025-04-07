package com.microservice.product.utility;

import com.microservice.product.dto.CategoryResponse;
import com.microservice.product.dto.ProductPurchaseResponse;
import com.microservice.product.dto.ProductRequest;
import com.microservice.product.dto.ProductResponse;
import com.microservice.product.model.Category;
import com.microservice.product.model.Product;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class ModelUtility {

    public Product toModel(Product product, ProductRequest request, Category category) {
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        log.info("Category: {}", category);
        product.setCategory(category);
        return product;
    }

    public ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .description(product.getDescription())
                .category(CategoryResponse.builder()
                        .categoryId(product.getCategory().getId())
                        .categoryName(product.getCategory().getName())
                        .categoryDescription(product.getCategory().getDescription())
                        .build())
                .build();
    }

    public ProductPurchaseResponse toPurchaseResponse(Product product, Double quantity) {
        return ProductPurchaseResponse.builder()
                .Id(product.getId())
                .Name(product.getName())
                .Description(product.getDescription())
                .Price(product.getPrice())
                .quantity(quantity)
                .build();
    }
}
