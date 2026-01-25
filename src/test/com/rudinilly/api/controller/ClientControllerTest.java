package com.rudinilly.api.controller;

import com.rudinilly.api.command.client.AddressCommand;
import com.rudinilly.api.command.client.ClientCommand;
import com.rudinilly.domain.model.entity.Client;
import com.rudinilly.domain.model.enums.UF;
import com.rudinilly.domain.service.ClientService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static io.restassured.RestAssured.given;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@QuarkusTest
class ClientControllerTest {

    @InjectMock
    ClientService clientService;

    private UUID clientId;

    @BeforeEach
    void setup() {
        clientId = UUID.randomUUID();
    }

    @Test
    void shouldCreateClient() {

        ClientCommand command = new ClientCommand(
                "João da Silva Pereira",
                "Maria da Silva",
                "joao@email.com",
                "12345678909",
                "MG123456",
                "31999999999",
                new AddressCommand(
                        "12345-678",
                        "Rua A",
                        "10",
                        "São Paulo",
                        UF.SP,
                        "Apto 1"
                ),
                LocalDate.of(1990, 1, 1)
        );

        given()
                .contentType(ContentType.JSON)
                .body(command)
                .when()
                .post("/clients")
                .then()
                .statusCode(201);

        Mockito.verify(clientService).create(Mockito.any());
    }

    @Test
    void shouldFindClientById() {
        given()
                .when()
                .get("/clients/" + clientId)
                .then()
                .statusCode(200);

        Mockito.verify(clientService).findById(clientId);
    }

    @Test
    void shouldFindAllClients() {
        given()
                .when()
                .get("/clients")
                .then()
                .statusCode(200);

        Mockito.verify(clientService).findAll();
    }

    @Test
    void shouldUpdateClient() {

        ClientCommand command = new ClientCommand(
                "João da Silva Pereira",
                "Maria da Silva",
                "joao@email.com",
                "12345678909",
                "MG123456",
                "31999999999",
                new AddressCommand(
                        "12345-678",
                        "Rua A",
                        "10",
                        "São Paulo",
                        UF.SP,
                        "Apto 1"
                ),
                LocalDate.of(1990, 1, 1)
        );

        given()
                .contentType(ContentType.JSON)
                .body(command)
                .when()
                .put("/clients/" + clientId)
                .then()
                .statusCode(200);

        Mockito.verify(clientService).update(Mockito.eq(clientId), Mockito.any());
    }

    @Test
    void shouldDeleteClient() {
        given()
                .when()
                .delete("/clients/" + clientId)
                .then()
                .statusCode(200);

        Mockito.verify(clientService).delete(clientId);
    }
}