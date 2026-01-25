package com.rudinilly.domain.service;

import com.rudinilly.api.dto.*;
import com.rudinilly.domain.repository.ClientRepository;
import com.rudinilly.domain.repository.OrderItemRepository;
import com.rudinilly.domain.repository.OrderRepository;
import com.rudinilly.domain.repository.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class ReportService {
    private final ClientRepository clientRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;

    public  ReportService(
            ClientRepository clientRepository,
            ProductRepository productRepository,
            OrderItemRepository orderItemRepository,
            OrderRepository orderRepository
    ) {
        this.clientRepository = clientRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
    }

    public List<NewClientReport> findClientsByYear(int year) {
        return clientRepository.findClientsByYear(year);
    }

    public List<OldestProductsReport> oldest() {
        return productRepository.findOldestProducts();
    }

    public List<TopProductDTO> top4ProductsByRevenue() {
        return orderItemRepository.top4ProductsByRevenue();
    }

    public RevenueReportDTO revenueLast12Months(LocalDate referenceDate) {
        List<MonthlyRevenueDTO> months =
                orderRepository.revenueLast12Months(referenceDate);
        BigDecimal totalRevenue = months.stream()
                .map(MonthlyRevenueDTO::revenue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal tax = totalRevenue
                .multiply(new BigDecimal("0.09"))
                .setScale(2, RoundingMode.HALF_UP);
        BigDecimal grandTotal = totalRevenue.add(tax);
        return new RevenueReportDTO(
                months,
                new RevenueSummaryDTO(totalRevenue, tax, grandTotal)
        );
    }

}
