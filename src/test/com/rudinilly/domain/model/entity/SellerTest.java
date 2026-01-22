package com.rudinilly.domain.model.entity;

import com.rudinilly.domain.model.enums.ProductType;
import com.rudinilly.domain.model.valueobject.Dimension;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SellerTest {
    Seller defaultSeller = createValidSeller();

    private Seller createValidSeller() {
        return new Seller(
                UUID.randomUUID(),
                "John Doe"
        );
    }

    @Test
    void shouldCreateSellerWhenAllDataIsValid() {
        Seller seller = createValidSeller();
        assertNotNull(seller);
        assertEquals("John Doe", seller.getName());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Seller(null, "John Doe");
        });
    }

    @Test
    void shouldThrowExceptionWhenNameIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Seller(UUID.randomUUID(), "");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Seller(UUID.randomUUID(), null);
        });
    }
}