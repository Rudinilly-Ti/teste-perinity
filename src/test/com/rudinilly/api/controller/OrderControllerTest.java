package com.rudinilly.api.controller;
import com.rudinilly.api.command.order.OrderCommand;
import com.rudinilly.domain.service.OrderService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@QuarkusTest
class OrderControllerTest {
    @InjectMock
    OrderService orderService;

    @Test
    void shouldCreateOrder() {
        given()
                .contentType(ContentType.JSON)
                .body("""
                {
                  "sellerId": "11111111-1111-1111-1111-111111111111",
                  "clientId": "22222222-2222-2222-2222-222222222222",
                  "paymentMethod": "MONEY",
                  "paidValue": 200,
                  "items": [
                    {
                      "productId": "33333333-3333-3333-3333-333333333333",
                      "quantity": 2
                    }
                  ]
                }
            """)
                .when()
                .post("/orders")
                .then()
                .statusCode(201);

        verify(orderService).create(any(OrderCommand.class));
    }

    @Test
    void shouldListOrders() {
        given()
                .when()
                .get("/orders")
                .then()
                .statusCode(200);

        verify(orderService).listOrders();
    }

    @Test
    void shouldFindOrdersByClientId() {
        UUID clientId = UUID.randomUUID();

        given()
                .when()
                .get("/orders/client/" + clientId)
                .then()
                .statusCode(200);

        verify(orderService).findByClientId(clientId);
    }

    @Test
    void shouldFindOrderById() {
        UUID id = UUID.randomUUID();
        given()
                .when()
                .get("/orders/" + id)
                .then()
                .statusCode(200);

        verify(orderService).findById(id);
    }

    @Test
    void shouldDeleteOrder() {
        UUID id = UUID.randomUUID();
        given()
                .when()
                .delete("/orders/" + id)
                .then()
                .statusCode(200);

        verify(orderService).deleteById(id);
    }
}