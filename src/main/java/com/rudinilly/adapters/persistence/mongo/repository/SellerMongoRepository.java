package com.rudinilly.adapters.persistence.mongo.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.rudinilly.adapters.persistence.mongo.document.SellerDocument;
import com.rudinilly.adapters.persistence.mongo.mapper.SellerMapper;
import com.rudinilly.domain.model.entity.Seller;
import com.rudinilly.domain.repository.SellerRepository;
import jakarta.enterprise.context.ApplicationScoped;

import static com.mongodb.client.model.Filters.eq;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class SellerMongoRepository implements SellerRepository {
    private final MongoCollection<SellerDocument> collection;

    public  SellerMongoRepository(MongoClient client) {
        this.collection = client
                .getDatabase("store")
                .getCollection("sellers", SellerDocument.class);
    }

    @Override
    public Seller save(Seller seller) {
        collection.insertOne(SellerMapper.toDocument(seller));

        return seller;
    }

    @Override
    public Optional<Seller> findById(UUID id) {
        SellerDocument document = collection.find(eq("_id", id)).first();
        if (document == null ) {
            return Optional.empty();
        }

        return Optional.of(SellerMapper.toEntity(document));
    }
}
