package com.rudinilly.domain.model.entity;

import com.rudinilly.domain.model.entity.OrderItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    @Test
    void shouldCreateOrderItemWhenAllDataIsValid() {
        UUID id = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        BigDecimal unityCost = new BigDecimal("10.50");
        Integer quantity = 3;

        OrderItem item = new OrderItem(id, productId, orderId, quantity, unityCost);

        assertEquals(id, item.getId());
        assertEquals(productId, item.getProductId());
        assertEquals(orderId, item.getOrderId());
        assertEquals(quantity, item.getQuantity());
        assertEquals(unityCost, item.getUnityCost());
        assertEquals(new BigDecimal("31.50"), item.getTotalCost());
    }

    @Test
    void shouldNotAllowNullId() {
        UUID productId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        BigDecimal unityCost = new BigDecimal("10.50");
        Integer quantity = 3;

        assertThrows(IllegalArgumentException.class, () ->{
            new OrderItem(null, productId, orderId, quantity, unityCost);
        });
    }

    @Test
    void shouldNotAllowNullProductId() {
        UUID id = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        BigDecimal unityCost = new BigDecimal("10.50");
        Integer quantity = 3;

        assertThrows(IllegalArgumentException.class, () ->{
            new OrderItem(id, null, orderId, quantity, unityCost);
        });
    }

    @Test
    void shouldNotAllowNullOrderId() {
        UUID id = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        BigDecimal unityCost = new BigDecimal("10.50");
        Integer quantity = 3;

        assertThrows(IllegalArgumentException.class, () ->{
            new OrderItem(id, productId, null, quantity, unityCost);
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = { 0, -1 })
    void shouldNotAllowInvalidQuantity(Integer value) {
        UUID id = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        BigDecimal unityCost = new BigDecimal("10.50");

        assertThrows(IllegalArgumentException.class, () ->
                new OrderItem(id, orderId, productId, value, unityCost)
        );
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "0", "-1" })
    void shouldNotAllowInvalidUnityCost(String value) {
        UUID id = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        Integer quantity = 2;

        BigDecimal unityCost = value == null ? null : new BigDecimal(value);

        assertThrows(IllegalArgumentException.class, () ->
                new OrderItem(id, orderId, productId, quantity, unityCost)
        );
    }
}