package com.microservice.product.service;

import com.microservice.product.dto.ProductRequest;
import com.microservice.product.dto.ProductResponse;
import com.microservice.product.model.Category;
import com.microservice.product.model.Product;
import com.microservice.product.repository.CategoryRepository;
import com.microservice.product.repository.ProductRepository;
import com.microservice.product.utility.ModelUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImp implements ProductService {

    private final ProductRepository repository;
    private final ValidateService validateService;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        validateRequest(productRequest);
        Product product = new Product();
        Category category = validateCategoryName(productRequest.getCategoryId());
        ModelUtility.toModel(product, productRequest, category);
        repository.save(product);
        return ModelUtility.toResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Integer id, ProductRequest productRequest) {
        validateRequest(productRequest);
        Product product = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found"));
        Category category = validateCategoryName(productRequest.getCategoryId());
        ModelUtility.toModel(product, productRequest, category);
        repository.save(product);
        return ModelUtility.toResponse(product);
    }


    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = repository.findAll();

        return products.stream()
                .map(ModelUtility::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(Integer id) {
        Product product = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found"));
        return ModelUtility.toResponse(product);
    }

    @Override
    public void deleteProductById(Integer id) {
        repository.deleteById(id);
    }

    @Override
    public List<String> getAllCategoriesNames() {
        return repository.findAllCategories().subList(0, 1);
    }

    private Category validateCategoryName(Integer request) {
        return categoryRepository.findById(request).orElseThrow(
                ()-> new RuntimeException("Category not found")
        );

    }

    private void validateRequest(ProductRequest request) {
        validateService.validate(request);
    }
//
//    @Override
//    public Page<ProductResponse> getAllProductsByCategory(String request) {
//        return repository.findAllByCategory_Name(request);
//    }
}
