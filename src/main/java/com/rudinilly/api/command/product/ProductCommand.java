package com.rudinilly.api.command.product;

import com.rudinilly.domain.model.enums.ProductType;
import com.rudinilly.domain.model.valueobject.Dimension;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductCommand(
        @NotBlank
        String name,
        @NotNull
        ProductType type,
        @NotBlank
        String productDetails,
        @NotNull
        @Valid
        DimensionCommand dimension,
        @NotNull
        Double weight,
        @NotNull
        BigDecimal buyPrice,
        @NotNull
        BigDecimal sellPrice
) { }
