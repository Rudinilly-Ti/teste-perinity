package com.rudinilly.adapters.persistence.mongo.mapper;

import com.rudinilly.adapters.persistence.mongo.document.SellerDocument;
import com.rudinilly.domain.model.entity.Seller;

public class SellerMapper {
    private SellerMapper() {}

    public static SellerDocument toDocument(Seller seller) {
        SellerDocument doc = new SellerDocument();
        doc.id = seller.getId();
        doc.name = seller.getName();

        return doc;
    }

    public static Seller toEntity(SellerDocument doc) {
        Seller seller = new Seller(doc.name);
        seller.setId(doc.id);

        return seller;
    }
}
