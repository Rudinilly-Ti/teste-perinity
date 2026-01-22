package com.rudinilly.domain.model.entity;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void shouldAddAnItemToItemsList() {
        Order order = new Order();
        UUID id = UUID.randomUUID();

        UUID productId = UUID.randomUUID();

        order.addItem(id, productId, 2, BigDecimal.TEN);

        assertEquals(1, order.getItems().size());
    }

    @Test
    void shouldNotAddAnItemToItemsListDueToZeroQuantity() {
        Order order = new Order();
        UUID id = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        assertThrows(IllegalArgumentException.class, () ->
            order.addItem(id, productId, 0, BigDecimal.TEN)
        );
    }

    @Test
    void shouldNotAddAnItemToItemsListDueToZeroUnityCost() {
        Order order = new Order();
        UUID id = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        assertThrows(IllegalArgumentException.class, () ->
                order.addItem(id, productId, 2, BigDecimal.ZERO)
        );
    }
}