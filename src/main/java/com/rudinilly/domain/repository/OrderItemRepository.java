package com.rudinilly.domain.repository;

import com.rudinilly.api.dto.TopProductDTO;
import com.rudinilly.domain.model.entity.OrderItem;

import java.util.List;
import java.util.UUID;

public interface OrderItemRepository {
    void saveAll(List<OrderItem> items);
    List<OrderItem> findByOrderId(UUID orderId);
    List<OrderItem> findByProductId(UUID productId);
    void deleteAllByOrderId(UUID orderId);
    List<TopProductDTO> top4ProductsByRevenue();
}
