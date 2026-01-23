package com.rudinilly.domain.model.entity;

import com.rudinilly.domain.model.enums.PaymentMethod;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class Order {
    private UUID id;
    private UUID sellerId;
    private UUID clientId;
    private PaymentMethod paymentMethod;
    private String cardNumber;
    private BigDecimal paidValue;
    private LocalDate orderDate;
    private BigDecimal totalOrder = BigDecimal.ZERO;
    private List<OrderItem> items = new ArrayList<>();

    public Order(UUID id, UUID sellerId, UUID clientId, PaymentMethod paymentMethod, String cardNumber, BigDecimal paidValue, LocalDate orderDate) {
        validateId(id);
        validateSellerId(sellerId);
        validateClientId(clientId);
        validatePaymentMethod(paymentMethod);
        validateCardNumber(cardNumber, paymentMethod);
        validatePaidValue(paidValue, paymentMethod);
        validateOrderDate(orderDate);
       
        this.id = id;
        this.sellerId = sellerId;
        this.clientId = clientId;
        this.paymentMethod = paymentMethod;
        this.cardNumber = cardNumber;
        this.paidValue = paidValue;
        this.orderDate = orderDate;
    }

    public void addItem(UUID id, UUID orderId, UUID productId, Integer quantity, BigDecimal unityCost) {
        OrderItem item = new OrderItem(id, productId, orderId, quantity, unityCost);
        items.add(item);
        calculateTotalOrder();
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    private void calculateTotalOrder() {
        this.totalOrder = items.stream()
                .map(OrderItem::getTotalCost)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private void validateId(UUID id) {
        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }
    }

    private void validateSellerId(UUID sellerId) {
        if (sellerId == null) {
            throw new IllegalArgumentException("Seller id cannot be null");
        }
    }

    private void validateClientId(UUID clientId) {
        if (clientId == null) {
            throw new IllegalArgumentException("Client id cannot be null");
        }
    }

    private void validatePaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod == null) {
            throw new IllegalArgumentException("Payment method cannot be null");
        }
    }

    private void validateCardNumber(String cardNumber, PaymentMethod paymentMethod) {
        if (paymentMethod == PaymentMethod.CREDIT_CARD &&
                (cardNumber == null || cardNumber.isBlank())) {
            throw new IllegalArgumentException("Card number cannot be empty");
        }
    }

    private void validatePaidValue(BigDecimal paidValue, PaymentMethod paymentMethod) {
        if (paymentMethod == PaymentMethod.MONEY &&
                (paidValue == null || paidValue.compareTo(BigDecimal.ZERO) <= 0)) {
            throw new IllegalArgumentException("Paid Value must be positive");
        }
    }

    private void validateOrderDate(LocalDate orderDate) {
        if(orderDate == null) {
            throw new IllegalArgumentException("Order date cannot be null");
        }

        if (orderDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Order date cannot be in the future");
        }

        if (orderDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Order date cannot be in the past");
        }
    }

    public UUID getId() {
        return id;
    }

    public UUID getSellerId() {
        return sellerId;
    }

    public UUID getClientId() {
        return clientId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public BigDecimal getPaidValue() {
        return paidValue;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public BigDecimal getTotalOrder() {
        return totalOrder;
    }
}
