package com.microservice.product.service;

import com.microservice.product.dto.*;
import com.microservice.product.exception.ProductPurchaseNotFound;
import com.microservice.product.exception.ResourceNotFoundEx;
import com.microservice.product.model.Category;
import com.microservice.product.model.Product;
import com.microservice.product.repository.ProductRepository;
import com.microservice.product.utility.ModelUtility;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Log4j2
public class ProductServiceImp implements ProductService {

    private final ProductRepository repository;
    private final ValidateService validateService;
    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        validateService.validate(productRequest);
        log.info("Adding product {}", productRequest.getName());
        Product product = new Product();
        log.info("tes");
        Category category = entityManager.find(Category.class, productRequest.getCategoryId());
        if (category == null) {
            log.error("Category not found");
            throw new ResourceNotFoundEx("Category not found");
        }
        log.info("category id {}", category.getId());
        ModelUtility.toModel(product, productRequest, category);
        log.info("category");
        log.info("product");
        repository.save(product);
        return ModelUtility.toResponse(product);
    }

    @Override
    public ProductResponse updateProduct(Integer id, ProductRequest productRequest) {
        validateService.validate(productRequest);
        Product product = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Product not found"));
        Category category = entityManager.find(Category.class, productRequest.getCategoryId());
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
        return repository.findAllCategories()
                .stream()
                .distinct()
                .toList();
    }

    @Override
    @Transactional(rollbackFor = ProductPurchaseNotFound.class)
    public List<ProductPurchaseResponse> getProductPurchases(List<ProductPurchaseRequest> requests) {
        List<Integer> productIds = requests
                .stream()
                .map(ProductPurchaseRequest::getProductId)
                .toList();

        List<Product> storedProducts = repository.findAllByIdInOrderById(productIds);
        if(productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseNotFound("One or more products not found");
        }
        List<ProductPurchaseRequest> storedRequest = requests
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::getProductId))
                .toList();

        List<ProductPurchaseResponse> purchasedProducts = new ArrayList<>();
        for(int i = 0; i < storedProducts.size(); i++) {
            Product products = storedProducts.get(i);
            ProductPurchaseRequest productRequest = storedRequest.get(i);
            if (products.getQuantity() < productRequest.getQuantity()) {
                throw new ProductPurchaseNotFound("Out of stock with id " + products.getId());
            }
            Double newAvaibleQuantity = products.getQuantity() - productRequest.getQuantity();
            products.setQuantity(newAvaibleQuantity);
            repository.save(products);
            purchasedProducts.add(ModelUtility.toPurchaseResponse(products, newAvaibleQuantity));
        }

        return purchasedProducts;
    }



//
//    @Override
//    public Page<ProductResponse> getAllProductsByCategory(String request) {
//        return repository.findAllByCategory_Name(request);
//    }
}
