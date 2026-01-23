package com.rudinilly.domain.model.entity;

import com.rudinilly.domain.model.entity.Order;
import com.rudinilly.domain.model.enums.PaymentMethod;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    Order defaultOrder = createValidOrder();

    private Order createValidOrder() {
        UUID id = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        UUID sellerId = UUID.randomUUID();
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;
        String cardNumber = "4324 4324 5467 2341";
        LocalDate orderDate = LocalDate.now();

        return new Order(
            id,
            sellerId,
            clientId,
            paymentMethod,
            cardNumber,
            BigDecimal.ZERO,
            orderDate
        );
    }

    @Test
    void shouldCreateAnOrder() {
        UUID id = UUID.randomUUID();
        UUID sellerId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;
        String cardNumber = "4324 4324 5467 2341";
        LocalDate orderDate = LocalDate.now();

        Order order = new Order(
                id,
                sellerId,
                clientId,
                paymentMethod,
                cardNumber,
                BigDecimal.ZERO,
                orderDate
        );

        assertNotNull(order);
        assertEquals(id, order.getId());
        assertEquals(sellerId, order.getSellerId());
        assertEquals(clientId, order.getClientId());
        assertEquals(paymentMethod, order.getPaymentMethod());
        assertEquals(cardNumber, order.getCardNumber());
        assertEquals(BigDecimal.ZERO, order.getPaidValue());
        assertEquals(orderDate, order.getOrderDate());

    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        UUID sellerId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;
        String cardNumber = "4324 4324 5467 2341";
        LocalDate orderDate = LocalDate.now();

        assertThrows(IllegalArgumentException.class, () -> {
            new Order(null, sellerId, clientId, paymentMethod, cardNumber, BigDecimal.ZERO, orderDate);
        });
    }

    @Test
    void shouldThrowExceptionWhenSellerIdIsNull() {
        UUID id = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;
        String cardNumber = "4324 4324 5467 2341";
        LocalDate orderDate = LocalDate.now();

        assertThrows(IllegalArgumentException.class, () -> {
            new Order(id, null, clientId, paymentMethod, cardNumber, BigDecimal.ZERO, orderDate);
        });
    }
    
    @Test
    void shouldThrowExceptionWhenClientIdIsNull() {
        UUID id = UUID.randomUUID();
        UUID sellerId = UUID.randomUUID();
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;
        String cardNumber = "4324 4324 5467 2341";
        LocalDate orderDate = LocalDate.now();

        assertThrows(IllegalArgumentException.class, () -> {
            new Order(id, sellerId, null, paymentMethod, cardNumber, BigDecimal.ZERO, orderDate);
        });
    }

    @Test
    void shouldThrowExceptionWhenPaymentMethodIsNull() {
        UUID id = UUID.randomUUID();
        UUID sellerId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        String cardNumber = "4324 4324 5467 2341";
        LocalDate orderDate = LocalDate.now();

        assertThrows(IllegalArgumentException.class, () -> {
            new Order(id, sellerId, clientId, null, cardNumber, BigDecimal.ZERO, orderDate);
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = "")
    void shouldThrowExceptionWhenPaymentMethodIsCreditCardAndCardNumberIsInvalid(String cardNumber) {
        UUID id = UUID.randomUUID();
        UUID sellerId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;
        LocalDate orderDate = LocalDate.now();

        assertThrows(IllegalArgumentException.class, () -> {
            new Order(id, sellerId, clientId, paymentMethod, cardNumber, BigDecimal.ZERO, orderDate);
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"0", "-1"})
    void shouldThrowExceptionWhenPaymentMethodIsMoneyAndPaidValueIsInvalid(String value) {
        BigDecimal paidValue = value == null ? null : new BigDecimal(value);
        UUID id = UUID.randomUUID();
        UUID sellerId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        PaymentMethod paymentMethod = PaymentMethod.MONEY;
        LocalDate orderDate = LocalDate.now();

        assertThrows(IllegalArgumentException.class, () -> {
            new Order(id, sellerId, clientId, paymentMethod, "", paidValue, orderDate);
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = { 1, -1 })
    void shouldThrowExceptionWhenOrderDateIsInvalid(Integer value) {
        UUID id = UUID.randomUUID();
        UUID sellerId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;
        String cardNumber = "4324 4324 5467 2341";
        LocalDate orderDate = value == null ? null : LocalDate.now().plusDays(value);

        assertThrows(IllegalArgumentException.class, () -> {
            new Order(id, sellerId, clientId, paymentMethod, cardNumber, BigDecimal.ZERO, orderDate);
        });
    }

    @Test
    void shouldAddAnItemToItemsList() {
        UUID id = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();

        defaultOrder.addItem(id, productId, orderId, 2, BigDecimal.TEN);

        assertEquals(1, defaultOrder.getItems().size());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = { 0, -1 })
    void shouldNotAddAnItemToItemsListDueToInvalidQuantity(Integer quantity) {
        UUID id = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        assertThrows(IllegalArgumentException.class, () ->
            defaultOrder.addItem(id, defaultOrder.getId(), productId, quantity, BigDecimal.TEN)
        );
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"0", "-1"})
    void shouldNotAddAnItemToItemsListDueToInvalidUnityCost(String value) {
        UUID id = UUID.randomUUID();
        UUID productId = UUID.randomUUID();
        BigDecimal unityCost = value == null ? null : new BigDecimal(value);

        assertThrows(IllegalArgumentException.class, () ->
            defaultOrder.addItem(id, defaultOrder.getId(), productId, 2, unityCost)
        );
    }
}