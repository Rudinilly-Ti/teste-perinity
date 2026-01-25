package com.rudinilly.api.command.order;

import com.rudinilly.domain.model.enums.PaymentMethod;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record OrderCommand (
        @NotNull
        UUID sellerId,
        @NotNull
        UUID clientId,
        @NotNull
        PaymentMethod paymentMethod,
        String cardNumber,
        BigDecimal paidValue,
        @Valid
        @NotNull
        @Size(min = 1)
        List<OrderItemCommand> items
) { }
