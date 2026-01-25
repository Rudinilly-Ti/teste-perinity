package com.rudinilly.domain.service;

import com.rudinilly.api.command.order.OrderCommand;
import com.rudinilly.api.command.order.OrderItemCommand;
import com.rudinilly.domain.model.entity.Order;
import com.rudinilly.domain.model.entity.OrderItem;
import com.rudinilly.domain.model.entity.Product;
import com.rudinilly.domain.model.enums.PaymentMethod;
import com.rudinilly.domain.repository.OrderItemRepository;
import com.rudinilly.domain.repository.OrderRepository;
import com.rudinilly.domain.repository.ProductRepository;
import jakarta.ws.rs.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    OrderRepository orderRepository;

    @Mock
    ProductRepository productRepository;

    @Mock
    OrderItemRepository orderItemRepository;

    @InjectMocks
    OrderService service;

    @Test
    void shouldCreateOrderAndPersistItems() {
        UUID productId = UUID.randomUUID();

        OrderItemCommand item = new OrderItemCommand(productId, 2);

        OrderCommand command = new OrderCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                PaymentMethod.MONEY,
                null,
                BigDecimal.valueOf(100),
                List.of(item)
        );

        Product product = Mockito.mock(Product.class);

        Mockito.when(product.getId()).thenReturn(productId);
        Mockito.when(product.getSellPrice()).thenReturn(BigDecimal.valueOf(50));

        Mockito.when(productRepository.findById(productId))
                .thenReturn(Optional.of(product));

        Order order = service.create(command);

        ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
        ArgumentCaptor<List<OrderItem>> captorOrderItem = ArgumentCaptor.forClass(List.class);

        Mockito.verify(orderRepository).save(captor.capture());
        Mockito.verify(orderItemRepository).saveAll(captorOrderItem.capture());

        assertEquals(1, order.getItems().size());
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {
        UUID productId = UUID.randomUUID();

        OrderItemCommand item = new OrderItemCommand(productId, 1);

        OrderCommand command = new OrderCommand(
                UUID.randomUUID(),
                UUID.randomUUID(),
                PaymentMethod.MONEY,
                null,
                BigDecimal.TEN,
                List.of(item)
        );

        Mockito.when(productRepository.findById(productId))
                .thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> service.create(command));
    }
}