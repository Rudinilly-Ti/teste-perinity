package com.rudinilly.domain.model.entity;

import com.rudinilly.domain.model.enums.ProductType;
import com.rudinilly.domain.model.valueobject.Dimension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SellerTest {

    @Test
    void shouldCreateSellerWhenAllDataIsValid() {
        Seller seller = new Seller(UUID.randomUUID(), "John Doe");
        assertNotNull(seller);
        assertEquals("John Doe", seller.getName());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Seller(null, "John Doe");
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings =  "")
    void shouldThrowExceptionWhenNameIsInvalid(String value) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Seller(UUID.randomUUID(), value);
        });
    }
}