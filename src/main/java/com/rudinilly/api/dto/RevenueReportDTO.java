package com.rudinilly.api.dto;
import java.util.List;

public record RevenueReportDTO (
        List<MonthlyRevenueDTO> months,
        RevenueSummaryDTO summary
) {}

