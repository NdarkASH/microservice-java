package com.microservice.product.utility;

import com.microservice.product.dto.CategoryResponse;
import com.microservice.product.dto.ProductRequest;
import com.microservice.product.dto.ProductResponse;
import com.microservice.product.model.Category;
import com.microservice.product.model.Product;
import com.microservice.product.repository.CategoryRepository;
import com.microservice.product.service.ValidateService;
import lombok.experimental.UtilityClass;
import org.springframework.stereotype.Service;

@UtilityClass
public class ModelUtility {

    public void toModel(Product product, ProductRequest request, Category category) {
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setQuantity(request.getQuantity());
        product.setCategory(category);
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
    };
}
