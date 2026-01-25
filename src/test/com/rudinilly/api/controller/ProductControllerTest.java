package com.rudinilly.api.controller;

import com.rudinilly.domain.service.ProductService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;

@QuarkusTest
class ProductControllerTest {

    @InjectMock
    ProductService productService;


    @Test
    void shouldCreateProduct() {
        given()
                .contentType(ContentType.JSON)
                .body("""
                {
                  "name": "Produto Teste",
                  "type": "SHOCK_ABSORBER",
                  "productDetails": "Detalhes",
                  "dimension": {
                    "width": 10,
                    "height": 10,
                    "depth": 10
                  },
                  "weight": 10,
                  "buyPrice": 50,
                  "sellPrice": 80
                }
            """)
                .when()
                .post("/products")
                .then()
                .statusCode(201);

        verify(productService).create(any());
    }

    @Test
    void shouldFindProductById() {
        UUID id = UUID.randomUUID();
        given()
                .when()
                .get("/products/" + id)
                .then()
                .statusCode(200);

        verify(productService).findById(id);
    }

    @Test
    void shouldFindAllProducts() {
        given()
                .when()
                .get("/products")
                .then()
                .statusCode(200);

        verify(productService).findAll();
    }

    @Test
    void shouldUpdateProduct() {
        UUID id = UUID.randomUUID();

        given()
                .contentType(ContentType.JSON)
                .body("""
                {
                  "name": "Produto Atualizado",
                  "type": "SHOCK_ABSORBER",
                  "productDetails": "Detalhes",
                  "dimension": {
                    "width": 10,
                    "height": 10,
                    "depth": 10
                  },
                  "weight": 10,
                  "buyPrice": 50,
                  "sellPrice": 80
                }
            """)
                .when()
                .put("/products/" + id)
                .then()
                .statusCode(200);

        verify(productService).update(eq(id), any());
    }

    @Test
    void shouldDeleteProduct() {

        UUID id = UUID.randomUUID();

        given()
                .when()
                .delete("/products/" + id)
                .then()
                .statusCode(200);

        verify(productService).delete(id);
    }
}