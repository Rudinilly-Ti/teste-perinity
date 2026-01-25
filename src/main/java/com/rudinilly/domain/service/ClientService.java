package com.rudinilly.domain.service;

import com.rudinilly.api.command.client.ClientCommand;
import com.rudinilly.domain.model.entity.Client;
import com.rudinilly.domain.model.valueobject.Address;
import com.rudinilly.domain.model.valueobject.CPF;
import com.rudinilly.domain.model.valueobject.Email;
import com.rudinilly.domain.repository.ClientRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ClientService {
    private final ClientRepository repository;

    public ClientService(ClientRepository repository){
        this.repository = repository;
    }

    public Client create(ClientCommand command) {
        Client client = new Client(
                command.fullName(),
                command.motherName(),
                new Email(command.email()),
                new CPF(command.cpf()),
                command.rg(),
                command.phone(),
                new Address(
                        command.address().cep(),
                        command.address().street(),
                        command.address().number(),
                        command.address().city(),
                        command.address().uf(),
                        command.address().complement()
                ),
                command.birthDate()
        );

        return repository.save(client);
    }

    public Client findById(UUID clientId) {
        return repository.findById(clientId).orElseThrow(() -> new NotFoundException("Client not found"));
    }

    public List<Client> findAll() {
        return repository.findAll();
    }

    public Client update(UUID clientId, ClientCommand command) {
        Client client = repository.findById(clientId).orElseThrow(() -> new NotFoundException("Client not found"));
        Client updatedClient = new Client(
            command.fullName(),
            command.motherName(),
            new Email(command.email()),
            new CPF(command.cpf()),
            command.rg(),
            command.phone(),
            new Address(
                    command.address().cep(),
                    command.address().street(),
                    command.address().number(),
                    command.address().city(),
                    command.address().uf(),
                    command.address().complement()
            ),
            command.birthDate()
        );

        updatedClient.setId(client.getId());
        updatedClient.setCreatedAt(client.getCreatedAt());

        return repository.update(updatedClient);
    }

    public void delete(UUID clientId) {
        repository.deleteById(clientId);
    }
}
