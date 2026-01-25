package com.rudinilly.adapters.persistence.mongo.document;

import com.rudinilly.domain.model.enums.PaymentMethod;
import org.bson.codecs.pojo.annotations.BsonId;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class OrderDocument {
    @BsonId
    public UUID id;

    public UUID sellerId;
    public UUID clientId;
    public PaymentMethod paymentMethod;
    public String cardNumber;
    public BigDecimal paidValue;
    public LocalDate createdAt;
    public BigDecimal totalOrder;
}
