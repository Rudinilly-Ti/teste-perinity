package com.rudinilly.domain.repository;

import com.rudinilly.api.dto.MonthlyRevenueDTO;
import com.rudinilly.domain.model.entity.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Order save(Order order);
    Optional<Order> findById(UUID id);
    List<Order> findAll();
    void deleteById(UUID id);

    List<Order> findByClientId(UUID clientId);
    List<Order> findByDate(LocalDate date);
    List<MonthlyRevenueDTO> revenueLast12Months(LocalDate referenceDate);
}
