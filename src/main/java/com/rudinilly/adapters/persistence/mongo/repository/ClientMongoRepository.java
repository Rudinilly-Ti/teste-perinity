package com.rudinilly.adapters.persistence.mongo.repository;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.rudinilly.adapters.persistence.mongo.document.ClientDocument;
import com.rudinilly.adapters.persistence.mongo.mapper.ClientMapper;
import com.rudinilly.api.dto.NewClientReport;
import com.rudinilly.domain.model.entity.Client;
import com.rudinilly.domain.repository.ClientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ClientMongoRepository implements ClientRepository {

    private final MongoCollection<ClientDocument> collection;

    public ClientMongoRepository(MongoClient client) {
        this.collection = client
                .getDatabase("store")
                .getCollection("clients", ClientDocument.class);
    }

    @Override
    public Client save(Client client) {
         collection.insertOne(ClientMapper.toDocument(client));
        return client;
    }

    @Override
    public Optional<Client> findById(UUID id) {
        ClientDocument document = collection.find(eq("_id", id)).first();

        if (document ==null ) {
            return Optional.empty();
        }
        return Optional.of(ClientMapper.toEntity(document));
    }

    @Override
    public List<Client> findAll() {
        return collection.find()
                .sort(ascending("fullName"))
                .map(ClientMapper::toEntity)
                .into(new ArrayList<>());
    }

    @Override
    public void deleteById(UUID id) {
        collection.deleteOne(eq("_id", id));
    }

    @Override
    public Client update(Client client) {
        collection.replaceOne(eq("_id", client.getId()), ClientMapper.toDocument(client));
        return client;
    }

    @Override
    public List<NewClientReport> findClientsByYear(int year) {
        LocalDate start = LocalDate.of(year, 1, 1);
        LocalDate end = LocalDate.of(year, 12, 31);

        Bson filter = Filters.and(
                Filters.gte("createdAt", start),
                Filters.lte("createdAt", end)
        );
        Bson projection = Projections.fields(
                Projections.include("_id", "fullName", "birthDate", "createdAt")
        );
        return collection.find(filter)
                .projection(projection)
                .map(doc -> {
                    return new NewClientReport(
                            doc.id,
                            doc.fullName,
                            doc.birthDate,
                            doc.createdAt
                    );
                })
                .into(new ArrayList<>());
    }
}
