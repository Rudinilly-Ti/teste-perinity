package com.rudinilly.domain.model.entity;

import com.rudinilly.domain.model.enums.PaymentMethod;
import com.rudinilly.domain.model.valueobject.OrderItem;

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
    private BigDecimal paidValor;
    private LocalDate orderDate;
    private BigDecimal totalOrder = BigDecimal.ZERO;
    private List<OrderItem> items = new ArrayList<>();

    public Order() {}

    public Order(UUID id, UUID sellerId, UUID clientId, PaymentMethod paymentMethod, String cardNumber, BigDecimal paidValor, LocalDate orderDate) {
        this.id = id;
        this.sellerId = sellerId;
        this.clientId = clientId;
        this.paymentMethod = paymentMethod;
        this.cardNumber = cardNumber;
        this.paidValor = paidValor;
        this.orderDate = orderDate;
    }

    public void addItem(UUID id, UUID productId, Integer quantity, BigDecimal unityCost) {
        OrderItem item = new OrderItem(id, productId, quantity, unityCost);
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

    public BigDecimal getPaidValor() {
        return paidValor;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public BigDecimal getTotalOrder() {
        return totalOrder;
    }
}
