package com.rudinilly.adapters.persistence.mongo.mapper;

import com.rudinilly.domain.model.valueobject.Address;
import com.rudinilly.adapters.persistence.mongo.document.AddressDocument;
import com.rudinilly.adapters.persistence.mongo.document.ClientDocument;
import com.rudinilly.domain.model.entity.Client;
import com.rudinilly.domain.model.valueobject.CPF;
import com.rudinilly.domain.model.valueobject.Email;

public final class ClientMapper {
    private ClientMapper() {}

    public static ClientDocument toDocument(Client client) {
        ClientDocument doc = new ClientDocument();

        doc.id = client.getId();
        doc.fullName = client.getFullName();
        doc.motherName = client.getMotherName();
        doc.email = client.getEmail().value();
        doc.cpf = client.getCpf().value();
        doc.rg = client.getRg();
        doc.phone = client.getPhone();
        doc.address = toDocument(client.getAddress());
        doc.birthDate = client.getBirthDate();
        doc.createdAt = client.getCreatedAt();

        return doc;
    }

    private static AddressDocument toDocument(Address address) {
        AddressDocument doc = new AddressDocument();
        doc.street = address.street();
        doc.city = address.city();
        doc.cep = address.cep();
        doc.uf = address.uf();
        doc.complement = address.complement();
        doc.number = address.number();
        return doc;
    }

    public static Client toEntity(ClientDocument doc) {
        Client client = new Client(
                doc.fullName,
                doc.motherName,
                new Email(doc.email),
                new CPF(doc.cpf),
                doc.rg,
                doc.phone,
                toEntity(doc.address),
                doc.birthDate
        );
        client.setId(doc.id);
        client.setCreatedAt(doc.createdAt);

        return client;
    }

    private static Address toEntity(AddressDocument doc) {
        if (doc == null) return null;

        return new Address(
                doc.cep,
                doc.street,
                doc.number,
                doc.city,
                doc.uf,
                doc.complement
        );
    }
}
