package com.rudinilly.domain.service;

import com.rudinilly.api.command.product.ProductCommand;
import com.rudinilly.domain.model.entity.Product;
import com.rudinilly.domain.model.valueobject.Dimension;
import com.rudinilly.domain.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository){
        this.repository = repository;
    }

    public Product create(ProductCommand command) {
        Product product = new Product(
                command.name(),
                command.type(),
                command.productDetails(),
                new Dimension(
                        command.dimension().width(),
                        command.dimension().height(),
                        command.dimension().depth()
                ),
                command.weight(),
                command.buyPrice(),
                command.sellPrice()
        );

        return repository.save(product);
    }

    public Product findById(UUID productId) {
        return repository.findById(productId).orElseThrow(() -> new NotFoundException("Client not found"));
    }

    public List<Product> findAll() {
        return repository.findAllOrderByName();
    }

    public Product update(UUID productId, ProductCommand command) {

        Product product = repository.findById(productId).orElseThrow(() -> new NotFoundException("Client not found"));
        Product updatedProduct = new Product(
                command.name(),
                command.type(),
                command.productDetails(),
                new Dimension(
                        command.dimension().width(),
                        command.dimension().height(),
                        command.dimension().depth()
                ),
                command.weight(),
                command.buyPrice(),
                command.sellPrice()
        );

        updatedProduct.setId(product.getId());
        updatedProduct.setCreatedAt(product.getCreatedAt());
        return repository.update(updatedProduct);
    }

    public void delete(UUID productId) {
        repository.deleteById(productId);
    }
}