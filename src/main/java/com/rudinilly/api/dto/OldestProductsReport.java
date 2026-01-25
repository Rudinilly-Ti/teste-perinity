package com.rudinilly.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public record OldestProductsReport (
        String name,
        Double weight,
        LocalDate createdAt,
        BigDecimal buyPrice
) {}
