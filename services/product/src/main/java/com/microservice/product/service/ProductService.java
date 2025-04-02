package com.microservice.product.service;


import com.microservice.product.dto.ProductRequest;
import com.microservice.product.dto.ProductResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    ProductResponse addProduct(ProductRequest productRequest);
    ProductResponse updateProduct(Integer id, ProductRequest productRequest);
    List<ProductResponse> getAllProducts();
    ProductResponse getProductById(Integer id);
    void deleteProductById(Integer id);
    List<String> getAllCategoriesNames();
//    Page<ProductResponse> getAllProductsByCategory(String category);


}
