package com.rudinilly.domain.model.entity;

import com.rudinilly.domain.model.enums.ProductType;
import com.rudinilly.domain.model.valueobject.Dimension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Product product = createValidProduct();

        assertNotNull(product);
        assertEquals("Produto Teste", product.getName());
        assertEquals(ProductType.EXTERIOR_FINISH, product.getType());
        assertEquals("Detalhes do produto", product.getProductDetails());
        assertEquals(new BigDecimal("10.00"), product.getBuyPrice());
        assertEquals(new BigDecimal("20.00"), product.getSellPrice());
        assertEquals(2.5, product.getWeight());
        assertEquals(LocalDate.now(), product.getRegistryDate());
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

    @Test
    void shouldThrowExceptionWhenNameIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    UUID.randomUUID(),
                    "",
                    ProductType.SHOCK_ABSORBER,
                    "Detalhes",
                    new Dimension(1.0,1.0,1.0),
                    1.0,
                    new BigDecimal("10"),
                    new BigDecimal("20"),
                    LocalDate.now()
            );
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    UUID.randomUUID(),
                    null,
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

    @Test
    void shouldThrowExceptionWhenBuyPriceIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    UUID.randomUUID(),
                    "Produto",
                    ProductType.EXTERIOR_FINISH,
                    "Detalhes",
                    new Dimension(1.0,1.0,1.0),
                    1.0,
                    BigDecimal.ZERO,
                    new BigDecimal("20"),
                    LocalDate.now()
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
                    new BigDecimal("-3"),
                    new BigDecimal("20"),
                    LocalDate.now()
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
                    null,
                    new BigDecimal("20"),
                    LocalDate.now()
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenSellPriceIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Product(
                    UUID.randomUUID(),
                    "Produto",
                    ProductType.EXTERIOR_FINISH,
                    "Detalhes",
                    new Dimension(1.0,1.0,1.0),
                    1.0,
                    new BigDecimal("10"),
                    new BigDecimal("-3"),
                    LocalDate.now()
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
                    BigDecimal.ZERO,
                    LocalDate.now()
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
                    null,
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

    @Test
    void shouldNotChangeNameWhenIsInvalid() {
        assertThrows(IllegalArgumentException.class, () ->
                defaultProduct.changeName("")
        );
        assertThrows(IllegalArgumentException.class, () ->
                defaultProduct.changeName(null)
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

    @Test
    void shouldNotChangeSellPriceWhenIsInvalid() {
        assertThrows(IllegalArgumentException.class, () ->
                defaultProduct.changeSellPrice(new BigDecimal("-3"))
        );
        assertThrows(IllegalArgumentException.class, () ->
                defaultProduct.changeSellPrice(BigDecimal.ZERO)
        );
        assertThrows(IllegalArgumentException.class, () ->
                defaultProduct.changeSellPrice(null)
        );
    }

    @Test
    void shouldChangeBuyPriceWhenIsValid() {
        defaultProduct.changeBuyPrice(new BigDecimal("300"));
        assertEquals(new BigDecimal("300"), defaultProduct.getBuyPrice());
    }

    @Test
    void shouldNotChangeBuyPriceWhenIsInvalid() {
        assertThrows(IllegalArgumentException.class, () ->
                defaultProduct.changeBuyPrice(new BigDecimal("-3"))
        );
        assertThrows(IllegalArgumentException.class, () ->
                defaultProduct.changeBuyPrice(BigDecimal.ZERO)
        );
        assertThrows(IllegalArgumentException.class, () ->
                defaultProduct.changeBuyPrice(null)
        );
    }

    @Test
    void shouldUpdateDetailsWhenIsValid() {
        defaultProduct.updateDetails("Engine");
        assertEquals("Engine", defaultProduct.getProductDetails());
    }

    @Test
    void shouldNotUpdateDetailsWhenIsInvalid() {
        assertThrows(IllegalArgumentException.class, () ->
                defaultProduct.updateDetails("")
        );
        assertThrows(IllegalArgumentException.class, () ->
                defaultProduct.updateDetails(null)
        );
    }

    @Test
    void shouldUpdateDimensionWhenIsValid() {
        Dimension dimension = new Dimension(3.3, 4.5, 5.0);
        defaultProduct.updateDimension(dimension);

        assertEquals(dimension, defaultProduct.getDimension());
    }

    @Test
    void shouldNotUpdateDimensionWhenIsInvalid() {
        assertThrows(IllegalArgumentException.class, () ->
                defaultProduct.updateDimension(null)
        );
    }

    @Test
    void shouldUpdateWeightWhenIsValid() {
        defaultProduct.updateWeight(4.6);
        assertEquals(4.6, defaultProduct.getWeight());
    }

    @Test
    void shouldNotUpdateWeightWhenIsInvalid() {
        assertThrows(IllegalArgumentException.class, () ->
                defaultProduct.updateWeight(-5.9)
        );
        assertThrows(IllegalArgumentException.class, () ->
                defaultProduct.updateWeight(0.0)
        );
        assertThrows(IllegalArgumentException.class, () ->
                defaultProduct.updateWeight(null)
        );
    }
}