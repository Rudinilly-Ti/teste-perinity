package com.rudinilly.adapters.persistence.mongo.document;

import org.bson.codecs.pojo.annotations.BsonId;

import java.time.LocalDate;
import java.util.UUID;

public class ClientDocument {
    @BsonId
    public UUID id;

    public String fullName;
    public String motherName;
    public String email;
    public String cpf;
    public String rg;
    public String phone;
    public AddressDocument address;
    public LocalDate birthDate;
    public LocalDate createdAt;
}
