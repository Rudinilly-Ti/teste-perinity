package com.rudinilly.domain.model.valueobject;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    @Test
    void shouldCalculateTotalCostCorrectly() {
        UUID id = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        BigDecimal unityCost = new BigDecimal("10.50");
        Integer quantity = 3;

        OrderItem item = new OrderItem(id, productId, quantity, unityCost);

        assertEquals(new BigDecimal("31.50"), item.getTotalCost());
    }

    @Test
    void shouldNotAllowZeroQuantity() {
        UUID id = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        BigDecimal unityCost = new BigDecimal("10.50");
        Integer quantity = 0;

        assertThrows(IllegalArgumentException.class, () ->
                new OrderItem(id, productId, quantity, unityCost)
        );
    }

    @Test
    void shouldNotAllowNegativeUnityCost() {
        UUID id = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        BigDecimal unityCost = new BigDecimal("-1");
        Integer quantity = 2;

        assertThrows(IllegalArgumentException.class, () ->
                new OrderItem(id, productId, quantity, unityCost)
        );
    }
}