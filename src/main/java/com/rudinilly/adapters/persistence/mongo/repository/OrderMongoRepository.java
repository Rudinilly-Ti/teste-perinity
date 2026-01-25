package com.rudinilly.adapters.persistence.mongo.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import com.rudinilly.adapters.persistence.mongo.document.OrderDocument;
import com.rudinilly.adapters.persistence.mongo.mapper.OrderMapper;
import com.rudinilly.api.dto.MonthlyRevenueDTO;
import com.rudinilly.domain.model.entity.Order;
import com.rudinilly.domain.repository.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.Decimal128;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

import static com.mongodb.client.model.Filters.eq;

@ApplicationScoped
public class OrderMongoRepository implements OrderRepository {
    private final MongoCollection<OrderDocument> collection;
    private final MongoClient client;
    public OrderMongoRepository(MongoClient client) {
        this.collection = client
                .getDatabase("store")
                .getCollection("orders", OrderDocument.class);
        this.client = client;
    }

    @Override
    public Order save(Order order) {
        collection.insertOne(OrderMapper.toDocument(order));
        return order;
    }

    @Override
    public Optional<Order> findById(UUID id) {
        OrderDocument document = collection.find(eq("_id", id)).first();

        if (document ==null ) {
            return Optional.empty();
        }

        return Optional.of(OrderMapper.toEntity(document));
    }

    @Override
    public List<Order> findAll() {
        return collection
                .find()
                .map(OrderMapper::toEntity)
                .into(new ArrayList<>());
    }

    @Override
    public void deleteById(UUID id) {
        collection.deleteOne(eq("_id", id));
    }

    @Override
    public List<Order> findByClientId(UUID clientId) {
        return collection
                .find(eq("clientId", clientId))
                .map(OrderMapper::toEntity)
                .into(new ArrayList<>());
    }

    @Override
    public List<Order> findByDate(LocalDate date) {
        return List.of();
    }

    public List<MonthlyRevenueDTO> revenueLast12Months(LocalDate referenceDate) {
        MongoCollection<Document> collection = client
                    .getDatabase("store")
                    .getCollection("orders");

        LocalDate start = referenceDate.minusMonths(11).withDayOfMonth(1);
        LocalDate end = referenceDate.withDayOfMonth(referenceDate.lengthOfMonth());

        Date startDate = Date.from(start.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Date endDate = Date.from(end.atTime(23,59,59).atZone(ZoneId.systemDefault()).toInstant());

        List<Bson> pipeline = List.of(

                Aggregates.match(
                        Filters.and(
                                Filters.gte("createdAt", startDate),
                                Filters.lte("createdAt", endDate)
                        )
                ),

                Aggregates.group(
                        new Document()
                                .append("year", new Document("$year", "$createdAt"))
                                .append("month", new Document("$month", "$createdAt")),
                        Accumulators.sum("revenue", "$totalOrder")
                ),

                Aggregates.sort(Sorts.ascending("_id.year", "_id.month")),

                Aggregates.limit(12)
        );

        List<MonthlyRevenueDTO> result = new ArrayList<>();

        for (Document doc : collection.aggregate(pipeline)) {

            Document id = doc.get("_id", Document.class);

            int year = id.getInteger("year");
            int month = id.getInteger("month");

            BigDecimal revenue =
                    doc.get("revenue", Decimal128.class).bigDecimalValue();

            result.add(new MonthlyRevenueDTO(year, month, revenue));
        }

        return result;
    }

}
