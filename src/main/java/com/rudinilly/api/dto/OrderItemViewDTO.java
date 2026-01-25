package com.rudinilly.api.dto;

import java.math.BigDecimal;

public class OrderItemViewDTO {
    public String productName;
    public Integer quantity;
    public BigDecimal unitCost;
    public BigDecimal totalCost;
}