package com.rudinilly.domain.model.valueobject;

import com.rudinilly.domain.model.enums.UF;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    private String stringWithLength(int length) {
        return "a".repeat(length);
    }

    @ParameterizedTest
    @ValueSource(strings = {"12345-678", "12345678"})
    void shouldCreateAddressWhenAllFieldsAreValid(String cep) {
        Address address = new Address(
            cep,
            "Av. Paulista",
            "100",
            "São Paulo",
            UF.SP,
            "Apto 10"
        );

        assertNotNull(address);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = "")
    void shouldThrowExceptionWhenCepIsNullOrBlank(String cep) {
        String street = "Av. Paulista";
        String number = "1000";
        String city = "São Paulo";
        UF uf = UF.SP;
        String complement = "Apt 10";

        assertThrows(IllegalArgumentException.class, () -> {
            Address address = new Address(
                cep, street, number, city, uf, complement
            );
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "1234a678",
        "12.345-678"
    })
    void shouldThrowExceptionWhenCepHasInvalidCharacters(String cep) {
        String street = "Av. Paulista";
        String number = "1000";
        String city = "São Paulo";
        UF uf = UF.SP;
        String complement = "Apt 10";

        assertThrows(IllegalArgumentException.class, () -> {
            Address address = new Address(
                cep, street, number, city, uf, complement
            );
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "1234567",
        "123456789"
    })
    void shouldThrowExceptionWhenCepHasInvalidLength(String cep) {
        String street = "Av. Paulista";
        String number = "1000";
        String city = "São Paulo";
        UF uf = UF.SP;
        String complement = "Apt 10";

        assertThrows(IllegalArgumentException.class, () -> {
            Address address = new Address(
                cep, street, number, city, uf, complement
            );
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = "")
    void shouldThrowExceptionWhenStreetIsNullOrBlank(String street) {
        String cep =  "12345-678";
        String number = "1000";
        String city = "São Paulo";
        UF uf = UF.SP;
        String complement = "Apt 10";

        assertThrows(IllegalArgumentException.class, () -> {
            new Address(
                cep, street, number, city, uf, complement
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenStreetExceedsMaxLength() {
        String cep =  "12345-678";
        String street = stringWithLength(151);
        String number = "1000";
        String city = "São Paulo";
        UF uf = UF.SP;
        String complement = "Apt 10";

        assertThrows(IllegalArgumentException.class, () -> {
            new Address(
                cep, street, number, city, uf, complement
            );
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = "")
    void shouldThrowExceptionWhenNumberIsNullOrBlank(String number) {
        String cep =  "12345-678";
        String street = "Av. Paulista";
        String city = "São Paulo";
        UF uf = UF.SP;
        String complement = "Apt 10";
        
        assertThrows(IllegalArgumentException.class, () -> {
            Address address = new Address(
                cep, street, number, city, uf, complement
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenNumberExceedsMaxLength() {
        String number = "1".repeat(11);
        String cep =  "12345-678";
        String street = "Av. Paulista";
        String city = "São Paulo";
        UF uf = UF.SP;
        String complement = "Apt 10";
        
        assertThrows(IllegalArgumentException.class, () -> {
            Address address = new Address(
                cep, street, number, city, uf, complement
            );
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"12#", "10 5"})
    void shouldThrowExceptionWhenNumberContainsInvalidCharacters(String number) {
        String cep =  "12345-678";
        String street = "Av. Paulista";
        String city = "São Paulo";
        UF uf = UF.SP;
        String complement = "Apt 10";
        
        assertThrows(IllegalArgumentException.class, () -> {
            Address address = new Address(
                cep, street, number, city, uf, complement
            );
        });
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = "")
    void shouldThrowExceptionWhenCityIsNullOrBlank(String city) {
        String cep =  "12345-678";
        String street = "Av. Paulista";
        String number = "1000";
        UF uf = UF.SP;
        String complement = "Apt 10";
        
        assertThrows(IllegalArgumentException.class, () -> {
            Address address = new Address(
                cep, street, number, city, uf, complement
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenCityExceedsMaxLength() {
        String cep =  "12345-678";
        String street = "Av. Paulista";
        String number = "1000";
        String city = stringWithLength(101);
        UF uf = UF.SP;
        String complement = "Apt 10";

        assertThrows(IllegalArgumentException.class, () -> {
            Address address = new Address(
                cep, street, number, city, uf, complement
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenUFIsNull() {
        String cep =  "12345-678";
        String street = "Av. Paulista";
        String number = "1000";
        String city = "São Paulo";
        String complement = "Apt 10";

        assertThrows(IllegalArgumentException.class, () -> {
            Address address = new Address(
                cep, street, number, city, null, complement
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenComplementExceedsMaxLength() {
        String cep =  "12345-678";
        String street = "Av. Paulista";
        String number = "1000";
        String city = "São Paulo";
        UF uf = UF.SP;
        String complement = stringWithLength(101);

        assertThrows(IllegalArgumentException.class, () -> {
            Address address = new Address(
                cep, street, number, city, uf, complement
            );
        });
    }
}