package com.rudinilly.domain.service;

import com.rudinilly.api.command.client.AddressCommand;
import com.rudinilly.api.command.client.ClientCommand;
import com.rudinilly.domain.model.entity.Client;
import com.rudinilly.domain.model.enums.UF;
import com.rudinilly.domain.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {
    @Mock
    ClientRepository repository;

    @InjectMocks
    ClientService service;

    @Test
    void shouldCreateClientAndSaveInRepository() {
        ClientCommand command = new ClientCommand(
                "João da Silva",
                "Maria da Silva",
                "joao@email.com",
                "12345678909",
                "MG123456",
                "31999999999",
                new AddressCommand(
                        "12345-678",
                        "Av. Paulista",
                        "100",
                        "São Paulo",
                        UF.SP,
                        "Apto 10"
                ),
                LocalDate.of(1990, 1, 1)
        );

        service.create(command);

        ArgumentCaptor<Client> captor = ArgumentCaptor.forClass(Client.class);

        Mockito.verify(repository).save(captor.capture());

        Client savedClient = captor.getValue();

        assertEquals("João da Silva", savedClient.getFullName());
        assertEquals("joao@email.com", savedClient.getEmail().value());
    }
}