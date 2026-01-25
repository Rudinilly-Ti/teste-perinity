package com.rudinilly.domain.repository;

import com.rudinilly.api.dto.OldestProductsReport;
import com.rudinilly.domain.model.entity.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(UUID id);
    void deleteById(UUID id);
    Product update(Product product);
    List<Product> findAllOrderByName();
    List<OldestProductsReport> findOldestProducts();
}
