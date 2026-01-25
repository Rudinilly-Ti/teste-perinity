package com.rudinilly.api.command.client;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public record ClientCommand(
        @NotNull
        @NotBlank
        String fullName,
        @NotNull
        @NotBlank
        String motherName,
        @NotNull
        @NotBlank
        @Email
        String email,
        @NotNull
        @NotBlank
        @Size(min = 11, max = 11)
        String cpf,
        @NotNull
        @NotBlank
        String rg,
        @NotNull
        @NotBlank
        String phone,
        @Valid
        @NotNull
        AddressCommand address,
        @NotNull
        LocalDate birthDate
) {
}
