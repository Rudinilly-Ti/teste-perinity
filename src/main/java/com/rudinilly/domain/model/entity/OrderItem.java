package com.rudinilly.domain.model.entity;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItem {
    private final UUID id;
    private final UUID productId;
    private final UUID orderId;
    private final Integer quantity;
    private final BigDecimal unityCost;
    private final BigDecimal totalCost;

    public OrderItem (UUID id, UUID productId, UUID orderId, Integer quantity, BigDecimal unityCost) {
        validateId(id);
        validateProductId(productId);
        validateOrderId(orderId);
        validateQuantity(quantity);
        validateUnityCost(unityCost);

        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.unityCost = unityCost;
        this.totalCost = unityCost.multiply(new BigDecimal(quantity));
    }

    private void validateId(UUID id) {
        if(id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }

    private void validateProductId(UUID productId) {
        if(productId == null) {
            throw new IllegalArgumentException("Product id cannot be null");
        }
    }

    private void validateOrderId(UUID orderId) {
        if(orderId == null) {
            throw new IllegalArgumentException("Order id cannot be null");
        }
    }

    private void validateQuantity(Integer quantity) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
    }

    private void validateUnityCost(BigDecimal unityCost) {
        if (unityCost == null || unityCost.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Unity cost must be positive");
        }
    }

    public UUID getId() {
        return id;
    }

    public UUID getProductId() {
        return productId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public BigDecimal getUnityCost() {
        return unityCost;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }
}
