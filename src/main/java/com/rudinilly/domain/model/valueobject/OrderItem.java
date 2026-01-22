package com.rudinilly.domain.model.valueobject;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItem {
    private final UUID id;
    private final UUID productId;
    private final Integer quantity;
    private final BigDecimal unityCost;
    private final BigDecimal totalCost;

    public OrderItem (UUID id, UUID productId, Integer quantity, BigDecimal unityCost) {
        if (quantity == null || quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        if (unityCost == null || unityCost.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Unity cost must be positive");
        }

        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.unityCost = unityCost;
        this.totalCost = unityCost.multiply(new BigDecimal(quantity));
    }

    public UUID getId() {
        return id;
    }

    public UUID getProductId() {
        return productId;
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
