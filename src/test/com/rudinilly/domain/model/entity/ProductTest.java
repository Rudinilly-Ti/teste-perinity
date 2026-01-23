package com.rudinilly.domain.model.entity;

import com.rudinilly.domain.model.enums.ProductType;
import com.rudinilly.domain.model.valueobject.Dimension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
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
                UUID.randomUUID(),
                "Produto Teste",
                ProductType.EXTERIOR_FINISH,
                "Detalhes do produto",
                new Dimension(10.0, 20.0, 30.0),
                2.5,
                new BigDecimal("10.00"),
                new BigDecimal("20.00"),
                LocalDate.now()
        );
    }

    @Test
    void shouldCreateProductWhenAllDataIsValid() {
        UUID id = UUID.randomUUID();
        String name = "Produto Teste";
        ProductType type = ProductType.EXTERIOR_FINISH;
        String details = "Detalhes do produto";
        Dimension dimension = new Dimension(10.0, 20.0, 30.0);
        Double weight =  2.5;
        BigDecimal buyPrice = new BigDecimal("10.00");
        BigDecimal sellPrice = new BigDecimal("20.00");
        LocalDate registryDate = LocalDate.now();


        Product product = new Product(
            id, name, type, details, dimension, weight, buyPrice, sellPrice, registryDate
        );

        assertNotNull(product);
        assertEquals(id, product.getId());
        assertEquals(name, product.getName());
        assertEquals(type, product.getType());
        assertEquals(details, product.getProductDetails());
        assertEquals(buyPrice, product.getBuyPrice());
        assertEquals(sellPrice, product.getSellPrice());
        assertEquals(weight, product.getWeight());
        assertEquals(registryDate, product.getRegistryDate());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    null,
                    "Produto",
                    ProductType.INTERIOR_FINISH,
                    "Detalhes",
                    new Dimension(1.0,1.0,1.0),
                    1.0,
                    new BigDecimal("10"),
                    new BigDecimal("20"),
                    LocalDate.now()
            );
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = "")
    void shouldThrowExceptionWhenNameIsInvalid(String name) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    UUID.randomUUID(),
                    name,
                    ProductType.SHOCK_ABSORBER,
                    "Detalhes",
                    new Dimension(1.0,1.0,1.0),
                    1.0,
                    new BigDecimal("10"),
                    new BigDecimal("20"),
                    LocalDate.now()
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenTypeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    UUID.randomUUID(),
                    "Produto",
                    null,
                    "Detalhes",
                    new Dimension(1.0,1.0,1.0),
                    1.0,
                    new BigDecimal("10"),
                    new BigDecimal("20"),
                    LocalDate.now()
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenDimensionIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    UUID.randomUUID(),
                    "Produto",
                    ProductType.INTERIOR_FINISH,
                    "Detalhes",
                    null,
                    1.0,
                    new BigDecimal("10"),
                    new BigDecimal("20"),
                    LocalDate.now()
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
                    UUID.randomUUID(),
                    "Produto",
                    ProductType.EXTERIOR_FINISH,
                    "Detalhes",
                    new Dimension(1.0,1.0,1.0),
                    1.0,
                    buyPrice,
                    new BigDecimal("20"),
                    LocalDate.now()
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
                    UUID.randomUUID(),
                    "Produto",
                    ProductType.EXTERIOR_FINISH,
                    "Detalhes",
                    new Dimension(1.0,1.0,1.0),
                    1.0,
                    new BigDecimal("10"),
                    sellPrice,
                    LocalDate.now()
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenRegistryDateIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    UUID.randomUUID(),
                    "Produto",
                    ProductType.EXTERIOR_FINISH,
                    "Detalhes",
                    new Dimension(1.0,1.0,1.0),
                    1.0,
                    new BigDecimal("10"),
                    new BigDecimal("20"),
                    LocalDate.now().plusDays(1)
            );
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    UUID.randomUUID(),
                    "Produto",
                    ProductType.EXTERIOR_FINISH,
                    "Detalhes",
                    new Dimension(1.0,1.0,1.0),
                    1.0,
                    new BigDecimal("10"),
                    new BigDecimal("20"),
                    LocalDate.now().minusDays(1)
            );
        });
    }

    @Test
    void shouldChangeNameWhenIsValid() {
        defaultProduct.changeName("Troca Nome");
        assertEquals("Troca Nome", defaultProduct.getName());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = "" )
    void shouldNotChangeNameWhenIsInvalid(String name) {
        assertThrows(IllegalArgumentException.class, () ->
                defaultProduct.changeName(name)
        );
    }

    @Test
    void shouldChangeTypeWhenIsValid() {
        defaultProduct.changeType(ProductType.SHOCK_ABSORBER);
        assertEquals(ProductType.SHOCK_ABSORBER, defaultProduct.getType());
    }

    @Test
    void shouldNotChangeTypeWhenIsInvalid() {
        assertThrows(IllegalArgumentException.class, () ->
                defaultProduct.changeType(null)
        );
    }

    @Test
    void shouldChangeSellPriceWhenIsValid() {
        defaultProduct.changeSellPrice(new BigDecimal("300"));
        assertEquals(new BigDecimal("300"), defaultProduct.getSellPrice());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "0", "-1" })
    void shouldNotChangeSellPriceWhenIsInvalid(String value) {
        BigDecimal sellPrice = value == null ? null : new BigDecimal(value);
        assertThrows(IllegalArgumentException.class, () ->
                defaultProduct.changeSellPrice(sellPrice)
        );
    }

    @Test
    void shouldChangeBuyPriceWhenIsValid() {
        defaultProduct.changeBuyPrice(new BigDecimal("300"));
        assertEquals(new BigDecimal("300"), defaultProduct.getBuyPrice());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "0", "-1" })
    void shouldNotChangeBuyPriceWhenIsInvalid(String value) {
        BigDecimal buyPrice = value == null ? null : new BigDecimal(value);
        assertThrows(IllegalArgumentException.class, () ->
                defaultProduct.changeBuyPrice(buyPrice)
        );
    }

    @Test
    void shouldChangeDetailsWhenIsValid() {
        defaultProduct.updateDetails("Engine");
        assertEquals("Engine", defaultProduct.getProductDetails());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = "")
    void shouldNotChangeDetailsWhenIsInvalid(String value) {
        assertThrows(IllegalArgumentException.class, () ->
                defaultProduct.updateDetails(value)
        );
    }

    @Test
    void shouldChangeDimensionWhenIsValid() {
        Dimension dimension = new Dimension(3.3, 4.5, 5.0);
        defaultProduct.updateDimension(dimension);

        assertEquals(dimension, defaultProduct.getDimension());
    }

    @Test
    void shouldNotChangeDimensionWhenIsInvalid() {
        assertThrows(IllegalArgumentException.class, () ->
                defaultProduct.updateDimension(null)
        );
    }

    @Test
    void shouldChangeWeightWhenIsValid() {
        defaultProduct.updateWeight(4.6);
        assertEquals(4.6, defaultProduct.getWeight());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(doubles = { 0.0, -1.0 })
    void shouldNotChangeWeightWhenIsInvalid(Double weight) {
        assertThrows(IllegalArgumentException.class, () ->
                defaultProduct.updateWeight(weight)
        );
    }
}