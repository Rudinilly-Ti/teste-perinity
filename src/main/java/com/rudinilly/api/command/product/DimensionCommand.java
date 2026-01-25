package com.rudinilly.api.command.product;

import jakarta.validation.constraints.NotNull;

public record DimensionCommand(
        @NotNull
        Double width,
        @NotNull
        Double height,
        @NotNull
        Double depth
) { }
