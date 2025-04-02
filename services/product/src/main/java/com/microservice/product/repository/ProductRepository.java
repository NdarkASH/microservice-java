package com.microservice.product.repository;

import com.microservice.product.dto.ProductResponse;
import com.microservice.product.model.Category;
import com.microservice.product.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("SELECT p.category.name FROM Product p")
    List<String> findAllCategories();

//    Page<ProductResponse> findAllByCategory_Name(String categoryName);
}
