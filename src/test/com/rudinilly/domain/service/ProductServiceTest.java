package com.rudinilly.domain.service;

import com.rudinilly.api.command.product.DimensionCommand;
import com.rudinilly.api.command.product.ProductCommand;
import com.rudinilly.domain.model.entity.Product;
import com.rudinilly.domain.model.enums.ProductType;
import com.rudinilly.domain.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    ProductRepository repository;

    @InjectMocks
    ProductService service;

    @Test
    void shouldCreateProductAndSaveInRepository() {
        ProductCommand command = new ProductCommand(
                "Amortecedor",
                ProductType.SHOCK_ABSORBER,
                "Corolla 2020",
                new DimensionCommand(10.0, 20.0, 30.0),
                5.0,
                BigDecimal.valueOf(200),
                BigDecimal.valueOf(350)
        );

        service.create(command);

        ArgumentCaptor<Product> captor = ArgumentCaptor.forClass(Product.class);

        Mockito.verify(repository).save(captor.capture());

        Product saved = captor.getValue();

        assertEquals("Amortecedor", saved.getName());
        assertEquals(ProductType.SHOCK_ABSORBER, saved.getType());
        assertEquals(BigDecimal.valueOf(350), saved.getSellPrice());
        assertEquals(5.0, saved.getWeight());
    }
}