package com.rudinilly.api.dto;

import java.math.BigDecimal;
import java.util.List;

public class OrderViewDTO {
    public String orderId;
    public String clientName;
    public String sellerName;
    public List<OrderItemViewDTO> items;
    public BigDecimal totalOrder;
    public BigDecimal tax; // 9%
    public BigDecimal totalWithTax;
    public String paymentMethod;
}
