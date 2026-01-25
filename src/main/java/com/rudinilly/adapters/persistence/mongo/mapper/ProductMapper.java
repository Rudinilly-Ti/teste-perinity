package com.rudinilly.adapters.persistence.mongo.mapper;

import com.rudinilly.domain.model.entity.Product;
import com.rudinilly.domain.model.valueobject.Dimension;
import com.rudinilly.adapters.persistence.mongo.document.DimensionDocument;
import com.rudinilly.adapters.persistence.mongo.document.ProductDocument;

public final class ProductMapper {
    private ProductMapper() {}

    public static ProductDocument toDocument(Product product) {
        ProductDocument doc = new ProductDocument();
        doc.id = product.getId();
        doc.name = product.getName();
        doc.type = product.getType();
        doc.productDetails = product.getProductDetails();
        doc.weight = product.getWeight();
        doc.dimension = toDocument(product.getDimension());
        doc.buyPrice = product.getBuyPrice();
        doc.sellPrice = product.getSellPrice();
        doc.createdAt = product.getCreatedAt();

        return doc;
    }

    private static DimensionDocument toDocument(Dimension dimension) {
        DimensionDocument doc = new DimensionDocument();
        doc.width = dimension.width();
        doc.height = dimension.height();
        doc.depth = dimension.depth();

        return doc;
    }

    public static Product toEntity(ProductDocument doc) {
        Product product = new Product(
                doc.name,
                doc.type,
                doc.productDetails,
                toEntity(doc.dimension),
                doc.weight,
                doc.buyPrice,
                doc.sellPrice
        );

        product.setId(doc.id);
        product.setCreatedAt(doc.createdAt);

        return product;
    }

    private static Dimension toEntity(DimensionDocument doc) {
        return new Dimension(
                doc.width,
                doc.height,
                doc.depth
        );
    }
}
