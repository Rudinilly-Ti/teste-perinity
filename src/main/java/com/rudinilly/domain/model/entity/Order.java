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
    private LocalDate createdAt;
    private BigDecimal totalOrder = BigDecimal.ZERO;
    private List<OrderItem> items = new ArrayList<>();

    public Order(UUID sellerId, UUID clientId, PaymentMethod paymentMethod, String cardNumber, BigDecimal paidValue) {
        validateSellerId(sellerId);
        validateClientId(clientId);
        validatePaymentMethod(paymentMethod);
        validateCardNumber(cardNumber, paymentMethod);
        validatePaidValue(paidValue, paymentMethod);
       
        this.id = UUID.randomUUID();
        this.sellerId = sellerId;
        this.clientId = clientId;
        this.paymentMethod = paymentMethod;
        this.cardNumber = cardNumber;
        this.paidValue = paidValue;
        this.createdAt = LocalDate.now();
    }

    public void addItem(UUID productId, Integer quantity, BigDecimal unitCost) {
        OrderItem item = new OrderItem(productId, this.id, quantity, unitCost);
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

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public void setTotalOrder (BigDecimal totalOrder) {
        this.totalOrder = totalOrder;
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

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public BigDecimal getTotalOrder() {
        return totalOrder;
    }
}
