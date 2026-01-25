package com.rudinilly.adapters.persistence.mongo.mapper;

import com.rudinilly.adapters.persistence.mongo.document.OrderItemDocument;
import com.rudinilly.domain.model.entity.OrderItem;

public class OrderItemMapper {
    public static OrderItemDocument toDocument(OrderItem item) {
        OrderItemDocument doc = new OrderItemDocument();
        doc.id = item.getId();
        doc.orderId = item.getOrderId();
        doc.productId = item.getProductId();
        doc.quantity = item.getQuantity();
        doc.unitCost = item.getUnityCost();
        doc.totalCost = item.getTotalCost();
        return doc;
    }

    public static OrderItem toEntity(OrderItemDocument doc) {
        OrderItem item = new OrderItem(
                doc.productId,
                doc.orderId,
                doc.quantity,
                doc.unitCost
        );

        item.setId(doc.id);
        return item;
    }

}
