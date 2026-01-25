package com.rudinilly.adapters.persistence.mongo.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.rudinilly.adapters.persistence.mongo.document.ProductDocument;
import com.rudinilly.adapters.persistence.mongo.mapper.ProductMapper;
import com.rudinilly.api.dto.OldestProductsReport;
import com.rudinilly.domain.model.entity.Product;
import com.rudinilly.domain.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;

@ApplicationScoped
public class ProductMongoRepository implements ProductRepository {
    private final MongoCollection<ProductDocument> collection;

    public ProductMongoRepository(MongoClient client) {
        this.collection = client
                .getDatabase("store")
                .getCollection("products", ProductDocument.class);
    }

    @Override
    public Product save(Product product) {
        collection.insertOne(ProductMapper.toDocument(product));
        return product;
    }

    @Override
    public Optional<Product> findById(UUID id) {
        ProductDocument document = collection.find(eq("_id", id)).first();

        if (document == null ) {
            return Optional.empty();
        }

        return Optional.of(ProductMapper.toEntity(document));
    }

    @Override
    public List<Product> findAllOrderByName() {
        return collection.find()
                .sort(ascending("name"))
                .map(ProductMapper::toEntity)
                .into(new ArrayList<>());
    }

    @Override
    public void deleteById(UUID id) {
        collection.deleteOne(eq("_id", id));
    }

    @Override
    public Product update(Product product) {
        collection.replaceOne(eq(product.getId()), ProductMapper.toDocument(product));
        return product;
    }

    @Override
    public List<OldestProductsReport> findOldestProducts() {
        List<Bson> pipeline = List.of(
                Aggregates.sort(Sorts.ascending("createdAt")),
                Aggregates.limit(3),
                Aggregates.sort(Sorts.descending("buyPrice")),
                Aggregates.project(Projections.fields(
                        Projections.include("name", "weight", "createdAt", "buyPrice")
                ))
        );

        return collection.aggregate(pipeline)
                .map(doc -> {
                    return new OldestProductsReport(
                            doc.name,
                            doc.weight,
                            doc.createdAt,
                            doc.buyPrice
                    );
                })
                .into(new ArrayList<>());
    }
}
