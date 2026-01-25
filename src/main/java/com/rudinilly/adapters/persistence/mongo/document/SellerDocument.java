package com.rudinilly.adapters.persistence.mongo.document;

import org.bson.codecs.pojo.annotations.BsonId;

import java.util.UUID;

public class SellerDocument {
    @BsonId
    public UUID id;
    public String name;
}
