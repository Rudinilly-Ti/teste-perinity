package com.rudinilly.domain.model.valueobject;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class CPFTest {
    @Test
    void shouldCreateCPFWhenValueIsValid() {
        CPF cpf = new CPF("12345678901");

        assertNotNull(cpf);
        assertEquals("12345678901", cpf.value());
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowExceptionWhenCPFIsNullOrBlank(String value) {
        assertThrows(IllegalArgumentException.class, () -> new CPF(value));
     }

    @ParameterizedTest
    @ValueSource(strings = {
            "1234567890",
            "123456789012",
            "12345abc901",
    })
    void shouldThrowExceptionWhenCPFIsInvalid(String value) {
        assertThrows(IllegalArgumentException.class, () -> new CPF(value));
    }
}