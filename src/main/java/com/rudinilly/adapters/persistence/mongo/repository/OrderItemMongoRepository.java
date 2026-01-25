package com.rudinilly.adapters.persistence.mongo.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.rudinilly.adapters.persistence.mongo.document.OrderItemDocument;
import com.rudinilly.adapters.persistence.mongo.mapper.OrderItemMapper;
import com.rudinilly.api.dto.TopProductDTO;
import com.rudinilly.domain.model.entity.OrderItem;
import com.rudinilly.domain.repository.OrderItemRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.Decimal128;

import static com.mongodb.client.model.Filters.eq;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class OrderItemMongoRepository implements OrderItemRepository {
    private final MongoCollection<OrderItemDocument> collection;
    private final MongoClient client;
    public OrderItemMongoRepository(MongoClient client) {
        this.collection = client
                .getDatabase("store")
                .getCollection("order_items", OrderItemDocument.class);
        this.client = client;
    }

    @Override
    public void saveAll(List<OrderItem> items) {
        List<OrderItemDocument> docs = items.stream()
                .map(OrderItemMapper::toDocument)
                .toList();

        collection.insertMany(docs);
    }

    @Override
    public List<OrderItem> findByOrderId(UUID orderId) {
        return collection
                .find(eq("orderId", orderId))
                .map(OrderItemMapper::toEntity)
                .into(new ArrayList<>());
    }

    @Override
    public List<OrderItem> findByProductId(UUID productId) {
        return collection
                .find(eq("productId", productId))
                .map(OrderItemMapper::toEntity)
                .into(new ArrayList<>());
    }

    @Override
    public void deleteAllByOrderId(UUID orderId) {
        collection.deleteMany(eq("orderId", orderId));
    }

    @Override
    public List<TopProductDTO> top4ProductsByRevenue() {
        MongoCollection<Document> collection =
                client.getDatabase("store")
                        .getCollection("order_items");

        List<Bson> pipeline = List.of(
                Aggregates.group(
                        "$productId",
                        Accumulators.sum("revenue", "$totalCost")
                ),
                Aggregates.sort(Sorts.descending("revenue")),
                Aggregates.limit(4),
                Aggregates.lookup(
                        "products",
                        "_id",
                        "_id",
                        "product"
                ),
                Aggregates.unwind("$product"),
                Aggregates.project(Projections.fields(
                        Projections.computed("productId", "$_id"),
                        Projections.computed("name", "$product.name"),
                        Projections.computed("sellPrice", "$product.sellPrice")
                ))
        );

        return collection.aggregate(pipeline)
                .map(doc -> {
                    return new TopProductDTO(
                            doc.get("productId", UUID.class),
                            doc.getString("name"),
                            doc.get("sellPrice", Decimal128.class).bigDecimalValue()
                    );
                })
                .into(new ArrayList<>());
    }
}