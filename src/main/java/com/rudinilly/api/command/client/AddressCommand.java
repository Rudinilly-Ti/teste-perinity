package com.rudinilly.api.command.client;

import com.rudinilly.domain.model.enums.UF;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record AddressCommand(
        @NotBlank
        @Pattern(regexp = "\\d{5}-?\\d{3}")
        String cep,
        @NotBlank
        @Size(max = 150)
        String street,
        @NotBlank
        @Pattern(regexp = "[0-9A-Za-z]+")
        @Size(max = 10)
        String number,
        @NotBlank
        @Size(max = 100)
        String city,
        @NotNull
        UF uf,
        @Size(max = 100)
        String complement
) {
}
