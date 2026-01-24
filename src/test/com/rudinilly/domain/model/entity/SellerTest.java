package com.rudinilly.domain.model.entity;

import com.rudinilly.domain.model.enums.ProductType;
import com.rudinilly.domain.model.valueobject.Dimension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SellerTest {

    @Test
    void shouldCreateSellerWhenAllDataIsValid() {
        Seller seller = new Seller("John Doe");
        assertNotNull(seller);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowExceptionWhenNameIsInvalid(String name) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Seller(name);
        });
    }
}