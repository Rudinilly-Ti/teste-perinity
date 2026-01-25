package com.rudinilly.api.command.order;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderItemCommand(
        @NotNull
        UUID productId,
        @NotNull
        Integer quantity
) { }
