package com.rudinilly.api.controller;

import com.rudinilly.domain.service.SellerService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static io.restassured.RestAssured.given;

import java.util.UUID;

@QuarkusTest
class SellerControllerTest {
    @InjectMock
    SellerService sellerService;

    @Test
    void shouldCreateSeller() {
        given()
                .contentType(ContentType.JSON)
                .body("""
                {
                  "name": "João Vendedor"
                }
            """)
                .when()
                .post("/sellers")
                .then()
                .statusCode(201);

        Mockito.verify(sellerService).create(Mockito.any());
    }

    @Test
    void shouldFindSellerById() {
        UUID id = UUID.randomUUID();
        given()
                .when()
                .get("/sellers/" + id)
                .then()
                .statusCode(200);

        Mockito.verify(sellerService).findById(id);
    }
}