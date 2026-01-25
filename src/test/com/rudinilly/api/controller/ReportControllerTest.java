package com.rudinilly.api.controller;

import com.rudinilly.domain.service.ReportService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.verify;

@QuarkusTest
class ReportControllerTest {
    @InjectMock
    ReportService reportService;

    @Test
    void shouldReturnNewClientsByYear() {
        given()
                .when()
                .get("/reports/new-clients?year=2026")
                .then()
                .statusCode(200);

        verify(reportService).findClientsByYear(2026);
    }

    @Test
    void shouldReturnOldestProducts() {
        given()
                .when()
                .get("/reports/oldest-products")
                .then()
                .statusCode(200);

        verify(reportService).oldest();
    }

    @Test
    void shouldReturnTopProducts() {
        given()
                .when()
                .get("/reports/top-products")
                .then()
                .statusCode(200);

        verify(reportService).top4ProductsByRevenue();
    }

    @Test
    void shouldReturnRevenueLast12Months() {

        LocalDate date = LocalDate.of(2026, 1, 1);
        given()
                .when()
                .get("/reports/revenue?date=2026-01-01")
                .then()
                .statusCode(200);

        verify(reportService).revenueLast12Months(date);
    }
}