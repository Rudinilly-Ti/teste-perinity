package com.rudinilly.domain.model.entity;

import com.rudinilly.domain.model.entity.Order;
import com.rudinilly.domain.model.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    private Order defaultOrder;

    @BeforeEach
    void setup() {
        defaultOrder = createValidOrder();
    }

    private Order createValidOrder() {
        UUID clientId = UUID.randomUUID();
        UUID sellerId = UUID.randomUUID();
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;
        String cardNumber = "4324 4324 5467 2341";

        return new Order(
            sellerId,
            clientId,
            paymentMethod,
            cardNumber,
            BigDecimal.ZERO
        );
    }

    @Test
    void shouldCreateAnOrderWhenAllFieldsAreValid() {
        UUID sellerId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;
        String cardNumber = "4324 4324 5467 2341";

        Order order = new Order(
                sellerId,
                clientId,
                paymentMethod,
                cardNumber,
                BigDecimal.ZERO
        );

        assertNotNull(order);
    }

    @Test
    void shouldThrowExceptionWhenSellerIdIsNull() {
        UUID clientId = UUID.randomUUID();
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;
        String cardNumber = "4324 4324 5467 2341";

        assertThrows(IllegalArgumentException.class, () -> {
            new Order(null, clientId, paymentMethod, cardNumber, BigDecimal.ZERO);
        });
    }
    
    @Test
    void shouldThrowExceptionWhenClientIdIsNull() {
        UUID sellerId = UUID.randomUUID();
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;
        String cardNumber = "4324 4324 5467 2341";

        assertThrows(IllegalArgumentException.class, () -> {
            new Order( sellerId, null, paymentMethod, cardNumber, BigDecimal.ZERO);
        });
    }

    @Test
    void shouldThrowExceptionWhenPaymentMethodIsNull() {
        UUID sellerId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        String cardNumber = "4324 4324 5467 2341";

        assertThrows(IllegalArgumentException.class, () -> {
            new Order(sellerId, clientId, null, cardNumber, BigDecimal.ZERO);
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowExceptionWhenPaymentMethodIsCreditCardAndCardNumberIsInvalid(String cardNumber) {
        UUID sellerId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;

        assertThrows(IllegalArgumentException.class, () -> {
            new Order(sellerId, clientId, paymentMethod, cardNumber, BigDecimal.ZERO);
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "0", "-1" })
    void shouldThrowExceptionWhenPaymentMethodIsMoneyAndPaidValueIsInvalid(String value) {
        BigDecimal paidValue = value == null ? null : new BigDecimal(value);
        UUID sellerId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        PaymentMethod paymentMethod = PaymentMethod.MONEY;

        assertThrows(IllegalArgumentException.class, () -> {
            new Order(sellerId, clientId, paymentMethod, "", paidValue);
        });
    }

    @Test
    void shouldAddAnItemToItemsList() {
        UUID id = UUID.randomUUID();
        UUID productId = UUID.randomUUID();

        defaultOrder.addItem(productId, 2, BigDecimal.TEN);

        assertEquals(1, defaultOrder.getItems().size());
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(ints = { 0, -1 })
    void shouldNotAddAnItemToItemsListDueToInvalidQuantity(Integer quantity) {
        UUID productId = UUID.randomUUID();

        assertThrows(IllegalArgumentException.class, () ->
            defaultOrder.addItem(productId, quantity, BigDecimal.TEN)
        );
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = { "0", "-1" })
    void shouldNotAddAnItemToItemsListDueToInvalidUnitCost(String value) {
        UUID productId = UUID.randomUUID();
        BigDecimal unitCost = value == null ? null : new BigDecimal(value);

        assertThrows(IllegalArgumentException.class, () ->
            defaultOrder.addItem(productId, 2, unitCost)
        );
    }
}