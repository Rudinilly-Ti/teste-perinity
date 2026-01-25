package com.rudinilly.api.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record TopProductDTO (
    UUID productId,
    String name,
    BigDecimal sellPrice
) {}
