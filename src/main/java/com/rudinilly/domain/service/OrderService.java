package com.rudinilly.domain.service;

import com.rudinilly.api.dto.OrderItemViewDTO;
import com.rudinilly.api.dto.OrderViewDTO;
import com.rudinilly.api.command.order.OrderCommand;
import com.rudinilly.api.command.order.OrderItemCommand;
import com.rudinilly.domain.model.entity.*;
import com.rudinilly.domain.repository.*;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.NotFoundException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class OrderService {
    private final OrderRepository repository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final ClientRepository clientRepository;
    private final SellerRepository sellerRepository;
    public OrderService(
            OrderRepository repository,
            ProductRepository productRepository,
            OrderItemRepository orderItemRepository,
            ClientRepository clientRepository,
            SellerRepository sellerRepository
    ){
        this.repository = repository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
        this.clientRepository = clientRepository;
        this.sellerRepository = sellerRepository;
    }

    public Order create(OrderCommand command) {
        Order order = new Order(
                command.sellerId(),
                command.clientId(),
                command.paymentMethod(),
                command.cardNumber(),
                command.paidValue()
        );

        for (OrderItemCommand item : command.items()) {
            Product product = productRepository.findById(item.productId())
                    .orElseThrow(() -> new NotFoundException("Product not found"));
            order.addItem(
                    product.getId(),
                    item.quantity(),
                    product.getSellPrice()
            );
        }

        repository.save(order);
        orderItemRepository.saveAll(order.getItems());
        return order;
    }

    public List<OrderViewDTO> listOrders() {
        List<Order> orders = repository.findAll();

        return orders.stream().map(order -> {
            OrderViewDTO dto = new OrderViewDTO();
            dto.orderId = order.getId().toString();

            dto.clientName = clientRepository.findById(order.getClientId())
                    .map(Client::getFullName)
                    .orElse("Cliente não encontrado");

            dto.sellerName = sellerRepository.findById(order.getSellerId())
                    .map(Seller::getName)
                    .orElse("Vendedor não encontrado");

            List<OrderItem> items = orderItemRepository.findByOrderId(order.getId());
            dto.items = items.stream().map(item -> {
                order.addItem(item.getProductId(), item.getQuantity(), item.getUnityCost());
                OrderItemViewDTO itemDto = new OrderItemViewDTO();
                itemDto.productName = productRepository.findById(item.getProductId())
                        .map(Product::getName)
                        .orElse("Produto não encontrado");
                itemDto.quantity = item.getQuantity();
                itemDto.unitCost = item.getUnityCost();
                itemDto.totalCost = item.getTotalCost();
                return itemDto;
            }).toList();

            dto.totalOrder = order.getTotalOrder();
            dto.tax = dto.totalOrder.multiply(BigDecimal.valueOf(0.09)).setScale(2, RoundingMode.HALF_UP);
            dto.totalWithTax = dto.totalOrder.add(dto.tax);

            dto.paymentMethod = order.getPaymentMethod().name();

            return dto;
        }).toList();
    }

    public List<Order> findByClientId(UUID clientId) {
        List<Order> orders = repository.findByClientId(clientId);

        for (Order order : orders) {
            List<OrderItem> items = orderItemRepository.findByOrderId(order.getId());

            items.forEach(item ->
                    order.addItem(
                            item.getProductId(),
                            item.getQuantity(),
                            item.getUnityCost()
                    ));
        }

        return orders;
    }

    public Order findById(UUID id) {
        Order order = repository.findById(id).orElseThrow(
                () -> new NotFoundException("Order not found")
        );

        List<OrderItem> items = orderItemRepository.findByOrderId(order.getId());

        items.forEach(item ->
                order.addItem(
                        item.getProductId(),
                        item.getQuantity(),
                        item.getUnityCost()
                ));

        return order;
    }

    public void deleteById(UUID id) {
        repository.deleteById(id);
        orderItemRepository.deleteAllByOrderId(id);
    }

}
