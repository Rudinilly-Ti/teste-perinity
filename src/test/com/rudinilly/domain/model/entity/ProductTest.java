package com.rudinilly.domain.model.entity;

import com.rudinilly.domain.model.enums.ProductType;
import com.rudinilly.domain.model.valueobject.Dimension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    Product defaultProduct = createValidProduct();

    private Product createValidProduct() {
        return new Product(
                "Produto Teste",
                ProductType.EXTERIOR_FINISH,
                "Detalhes do produto",
                new Dimension(10.0, 20.0, 30.0),
                2.5,
                new BigDecimal("10.00"),
                new BigDecimal("20.00")
        );
    }

    @Test
    void shouldCreateProductWhenAllDataIsValid() {
        String name = "Produto Teste";
        ProductType type = ProductType.EXTERIOR_FINISH;
        String details = "Detalhes do produto";
        Dimension dimension = new Dimension(10.0, 20.0, 30.0);
        Double weight =  2.5;
        BigDecimal buyPrice = new BigDecimal("10.00");
        BigDecimal sellPrice = new BigDecimal("20.00");


        Product product = new Product(
            name, type, details, dimension, weight, buyPrice, sellPrice
        );

        assertNotNull(product);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowExceptionWhenNameIsInvalid(String name) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    name,
                    ProductType.SHOCK_ABSORBER,
                    "Detalhes",
                    new Dimension(1.0,1.0,1.0),
                    1.0,
                    new BigDecimal("10"),
                    new BigDecimal("20")
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenTypeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    "Produto",
                    null,
                    "Detalhes",
                    new Dimension(1.0,1.0,1.0),
                    1.0,
                    new BigDecimal("10"),
                    new BigDecimal("20")
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenDimensionIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    "Produto",
                    ProductType.INTERIOR_FINISH,
                    "Detalhes",
                    null,
                    1.0,
                    new BigDecimal("10"),
                    new BigDecimal("20")
            );
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "0", "-1" })
    void shouldThrowExceptionWhenBuyPriceIsInvalid(String value) {
        BigDecimal buyPrice = value == null ? null : new BigDecimal(value);
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    "Produto",
                    ProductType.EXTERIOR_FINISH,
                    "Detalhes",
                    new Dimension(1.0,1.0,1.0),
                    1.0,
                    buyPrice,
                    new BigDecimal("20")
            );
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "0", "-1" })
    void shouldThrowExceptionWhenSellPriceIsInvalid(String value) {
        BigDecimal sellPrice = value == null ? null : new BigDecimal(value);
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    "Produto",
                    ProductType.EXTERIOR_FINISH,
                    "Detalhes",
                    new Dimension(1.0,1.0,1.0),
                    1.0,
                    new BigDecimal("10"),
                    sellPrice
            );
        });
    }
}