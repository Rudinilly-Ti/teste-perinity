package com.rudinilly.adapters.persistence.mongo.document;

import org.bson.codecs.pojo.annotations.BsonId;

import java.math.BigDecimal;
import java.util.UUID;

public class OrderItemDocument {
    @BsonId
    public UUID id;

    public UUID productId;
    public UUID orderId;
    public Integer quantity;
    public BigDecimal unitCost;
    public BigDecimal totalCost;
}
