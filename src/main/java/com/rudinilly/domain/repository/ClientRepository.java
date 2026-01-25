package com.rudinilly.domain.repository;

import com.rudinilly.api.dto.NewClientReport;
import com.rudinilly.domain.model.entity.Client;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository {
    Client save(Client client);
    Optional<Client> findById(UUID id);
    List<Client> findAll();
    void deleteById(UUID id);
    Client update(Client client);
    List<NewClientReport> findClientsByYear(int year);
}
