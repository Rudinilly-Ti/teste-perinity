package com.rudinilly.domain.model.entity;

import com.rudinilly.domain.model.enums.UF;
import com.rudinilly.domain.model.valueobject.Address;
import com.rudinilly.domain.model.valueobject.CPF;
import com.rudinilly.domain.model.valueobject.Email;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    private final String validFullName = "João da Silva";
    private final String validMotherName = "Maria da Silva";
    private final Email validEmail = new Email("joao.silva@example.com");
    private final CPF validCPF = new CPF("12345678909");
    private final String validRG = "12345678";
    private final String validPhone = "31912345678";
    private final Address validAddress =  createValidAddress();
    private final LocalDate validBirthDate = LocalDate.of(1990, 1, 1);

    private Address createValidAddress() {
        return new Address(
            "12345-678",
            "Av. Paulista",
            "100",
            "São Paulo",
            UF.SP,
            "Apto 10"
        );
    }

    @Test
    void shouldCreateClientWhenAllFieldsAreValid() {
        Client client = new Client(
                validFullName,
                validMotherName,
                validEmail,
                validCPF,
                validRG,
                validPhone,
                validAddress,
                validBirthDate
            );

        assertNotNull(client);
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowExceptionWhenFullNameIsNullOrBlank(String fullName) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Client(
                    fullName,
                    validMotherName,
                    validEmail,
                    validCPF,
                    validRG,
                    validPhone,
                    validAddress,
                    validBirthDate
            );
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowExceptionWhenMotherNameIsNullOrBlank(String motherName) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Client(
                    validFullName,
                    motherName,
                    validEmail,
                    validCPF,
                    validRG,
                    validPhone,
                    validAddress,
                    validBirthDate
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenEmailIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Client(
                    validFullName,
                    validMotherName,
                    null,
                    validCPF,
                    validRG,
                    validPhone,
                    validAddress,
                    validBirthDate
            );
        });
    }
    @ParameterizedTest
    @NullSource
    void shouldThrowExceptionWhenCPFIsNull(CPF cpf) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Client(
                    validFullName,
                    validMotherName,
                    validEmail,
                    cpf,
                    validRG,
                    validPhone,
                    validAddress,
                    validBirthDate
            );
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowExceptionWhenRGIsNullOrBlank(String rg) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Client(
                    validFullName,
                    validMotherName,
                    validEmail,
                    validCPF,
                    rg,
                    validPhone,
                    validAddress,
                    validBirthDate
            );
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    void shouldThrowExceptionWhenPhoneIsNullOrBlank(String phone) {
        assertThrows(IllegalArgumentException.class, () -> {
            new Client(
                    validFullName,
                    validMotherName,
                    validEmail,
                    validCPF,
                    validRG,
                    phone,
                    validAddress,
                    validBirthDate
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenAddressIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Client(
                    validFullName,
                    validMotherName,
                    validEmail,
                    validCPF,
                    validRG,
                    validPhone,
                    null,
                    validBirthDate
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenBirthDateIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Client(
                    validFullName,
                    validMotherName,
                    validEmail,
                    validCPF,
                    validRG,
                    validPhone,
                    validAddress,
                    null
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenBirthDateIsInTheFuture() {
        LocalDate futureDate = LocalDate.now().plusDays(1);

        assertThrows(IllegalArgumentException.class, () -> {
            new Client(
                    validFullName,
                    validMotherName,
                    validEmail,
                    validCPF,
                    validRG,
                    validPhone,
                    validAddress,
                    futureDate
            );
        });
    }

    @Test
    void shouldThrowExceptionWhenUserIsUnder18YearsOld() {
        LocalDate birthDate = LocalDate.now().minusYears(17);

        assertThrows(IllegalArgumentException.class, () -> {
            new Client(
                    validFullName,
                    validMotherName,
                    validEmail,
                    validCPF,
                    validRG,
                    validPhone,
                    validAddress,
                    birthDate
            );
        });
    }
}