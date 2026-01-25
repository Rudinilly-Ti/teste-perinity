package com.rudinilly.api.dto;

import java.math.BigDecimal;

public record RevenueSummaryDTO(
        BigDecimal totalRevenue,
        BigDecimal totalTax,
        BigDecimal grandTotal
) {}

