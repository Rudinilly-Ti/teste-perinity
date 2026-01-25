package com.rudinilly.api.dto;

import java.math.BigDecimal;

public record MonthlyRevenueDTO(
        int year,
        int month,
        BigDecimal revenue
) {}
