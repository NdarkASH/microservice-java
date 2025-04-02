package com.microservice.product.controller;

import com.microservice.product.dto.AppResponse;
import com.microservice.product.dto.CategoryResponse;
import com.microservice.product.dto.ProductRequest;
import com.microservice.product.dto.ProductResponse;
import com.microservice.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public AppResponse<ProductResponse> createProduct(@RequestBody ProductRequest request) {
        ProductResponse productResponse = productService.addProduct(request);
        return AppResponse.<ProductResponse>builder()
                .code(HttpStatus.CREATED.value())
                .message("Product created")
                .data(productResponse)
                .build();
    }
    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            path = "/{id}"
    )
    public AppResponse<ProductResponse> getProductById(@PathVariable Integer id) {
        ProductResponse response = productService.getProductById(id);
        return AppResponse.<ProductResponse>builder()
                .code(HttpStatus.OK.value())
                .message("Product found")
                .data(response)
                .build();
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.OK)
    public AppResponse<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.getAllProducts();
        return AppResponse.<List<ProductResponse>>builder()
                .code(HttpStatus.OK.value())
                .message("Product found")
                .data(products)
                .build();
    }

    @PutMapping(
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            path = "/{id}"
    )
    @ResponseStatus(HttpStatus.ACCEPTED)
    public AppResponse<ProductResponse> updateProduct(@PathVariable Integer id, @RequestBody ProductRequest request) {

        ProductResponse response = productService.updateProduct(id, request);
        return AppResponse.<ProductResponse>builder()
                .code(HttpStatus.ACCEPTED.value())
                .message("Product updated")
                .data(response)
                .build();
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public AppResponse<Void> deleteProduct(@PathVariable Integer id) {
        productService.deleteProductById(id);

        return AppResponse.<Void>builder()
                .code(HttpStatus.NO_CONTENT.value())
                .message("Product deleted")
                .build();
    }

    @GetMapping(path = "/categories")
    @ResponseStatus(HttpStatus.OK)
    public AppResponse<List<String>> getAllCategories() {
        List<String> response = productService.getAllCategoriesNames();

        return AppResponse.<List<String>>builder()
                .code(HttpStatus.OK.value())
                .message("All categories")
                .data(response)
                .build();
    }
}
