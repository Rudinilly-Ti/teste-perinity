package com.rudinilly.adapters.persistence.mongo.mapper;

import com.rudinilly.adapters.persistence.mongo.document.OrderDocument;
import com.rudinilly.domain.model.entity.Order;

public class OrderMapper {
    public static OrderDocument toDocument(Order order) {
        OrderDocument doc = new OrderDocument();
        doc.id = order.getId();
        doc.sellerId = order.getSellerId();
        doc.clientId = order.getClientId();
        doc.paymentMethod = order.getPaymentMethod();
        doc.cardNumber = order.getCardNumber();
        doc.paidValue = order.getPaidValue();
        doc.createdAt = order.getCreatedAt();
        doc.totalOrder = order.getTotalOrder();
        return doc;
    }

    public static Order toEntity(OrderDocument doc) {
        Order order = new Order(
                doc.sellerId,
                doc.clientId,
                doc.paymentMethod,
                doc.cardNumber,
                doc.paidValue
        );

        order.setId(doc.id);
        order.setCreatedAt(doc.createdAt);
        return order;
    }
}
