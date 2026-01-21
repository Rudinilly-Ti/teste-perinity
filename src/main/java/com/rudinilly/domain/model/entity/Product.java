package com.rudinilly.domain.model.entity;

import com.rudinilly.domain.model.valueobject.Dimension;
import com.rudinilly.domain.model.enums.ProductType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Product {
    private final UUID id;
    private String name;
    private ProductType type;
    private String productDetails;
    private Dimension dimension;
    private Double weight;
    private BigDecimal buyPrice;
    private BigDecimal sellPrice;
    private final LocalDate registryDate;

    public Product(UUID id, String name, ProductType type, String productDetails, Dimension dimension, Double weight, BigDecimal buyPrice, BigDecimal sellPrice, LocalDate registryDate) {
        validateInitialState(id, name, type, dimension, buyPrice, sellPrice, registryDate);

        this.id = id;
        this.name = name;
        this.type = type;
        this.productDetails = productDetails;
        this.dimension = dimension;
        this.weight = weight;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.registryDate = registryDate;
    }

    public void changeName(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        this.name = name;
    }

    public void changeType(ProductType newType) {
        if (newType == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }
        this.type = newType;
    }

    public void changeSellPrice(BigDecimal newPrice) {
        validateSellPrice(newPrice);
        this.sellPrice = newPrice;
    }

    public void changeBuyPrice(BigDecimal newPrice) {
        validateBuyPrice(newPrice);
        this.buyPrice = newPrice;
    }

    public void updateDetails(String details) {
        this.productDetails = details;
    }

    public void updateDimension(Dimension dimension) {
        if (dimension == null) {
            throw new IllegalArgumentException("Dimension must be valid");
        }
        this.dimension = dimension;
    }

    public void updateWeight(Double weight) {
        if (weight != null && weight <= 0) {
            throw new IllegalArgumentException("Weight must be positive");
        }
        this.weight = weight;
    }

    private void validateBuyPrice(BigDecimal buyPrice) {
        if (buyPrice == null || buyPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Buy price must be positive");
        }
    }

    private void validateSellPrice(BigDecimal sellPrice) {
        if (sellPrice == null || sellPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Sell price must be positive");
        }
    }

    private void validateRegistryDate(LocalDate date) {
        if(date == null) {
            throw new IllegalArgumentException("Registry date cannot be null");
        }

        if (date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Registry date cannot be in the future");
        }

        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Registry date cannot be in the past");
        }
    }

    private void validateInitialState(
            UUID id,
            String name,
            ProductType type,
            Dimension dimension,
            BigDecimal buyPrice,
            BigDecimal sellPrice,
            LocalDate registryDate
    ) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }

        if (type == null) {
            throw new IllegalArgumentException("Type cannot be null");
        }

        if (dimension == null) {
            throw new IllegalArgumentException("Dimension must be valid");
        }

        validateBuyPrice(buyPrice);
        validateSellPrice(sellPrice);
        validateRegistryDate(registryDate);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProductType getType() {
        return type;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public Double getWeight() {
        return weight;
    }

    public BigDecimal getBuyPrice() {
        return buyPrice;
    }

    public BigDecimal getSellPrice() {
        return sellPrice;
    }

    public LocalDate getRegistryDate() {
        return registryDate;
    }
}
