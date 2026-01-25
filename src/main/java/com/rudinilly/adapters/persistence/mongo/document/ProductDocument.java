package com.rudinilly.adapters.persistence.mongo.document;

import com.rudinilly.domain.model.enums.ProductType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.bson.codecs.pojo.annotations.BsonId;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class ProductDocument {
    @BsonId
    public UUID id;

    public String name;
    public ProductType type;
    public String productDetails;
    public DimensionDocument dimension;
    public Double weight;
    public BigDecimal buyPrice;
    public BigDecimal sellPrice;
    public LocalDate createdAt;
}
