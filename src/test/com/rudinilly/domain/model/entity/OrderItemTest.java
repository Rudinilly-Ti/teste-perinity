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
    void shouldCreateOrderItemWhenAllFieldAreValid() {
        UUID productId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        BigDecimal unitCost = BigDecimal.valueOf(10.50);
        Integer quantity = 3;

        OrderItem item = new OrderItem(productId, orderId, quantity, unitCost);

        assertNotNull(item);
        assertEquals(BigDecimal.valueOf(31.5), item.getTotalCost());
    }

    @Test
    void shouldThrowExceptionWhenProductIdIsNull() {
        UUID orderId = UUID.randomUUID();
        BigDecimal unitCost = BigDecimal.valueOf(10.50);
        Integer quantity = 3;

        assertThrows(IllegalArgumentException.class, () ->{
            new OrderItem(null, orderId, quantity, unitCost);
        });
    }

    @Test
    void shouldThrowExceptionWhenOrderIdIsNull() {
        UUID productId = UUID.randomUUID();
        BigDecimal unitCost = BigDecimal.valueOf(10.50);
        Integer quantity = 3;

        assertThrows(IllegalArgumentException.class, () ->{
            new OrderItem(productId, null, quantity, unitCost);
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = { 0, -1 })
    void shouldThrowExceptionWhenQuantityIsInvalid(Integer quantity) {
        UUID orderId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        BigDecimal unitCost = BigDecimal.valueOf(10.50);

        assertThrows(IllegalArgumentException.class, () ->
                new OrderItem(productId, orderId, quantity, unitCost)
        );
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(doubles = { 0.0, -1.0 })
    void shouldThrowExceptionWhenUnitCostIsInvalid(Double value) {
        UUID orderId = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        Integer quantity = 2;

        BigDecimal unitCost = value == null ? null : BigDecimal.valueOf(value);

        assertThrows(IllegalArgumentException.class, () ->
                new OrderItem(productId, orderId, quantity, unitCost)
        );
    }
}