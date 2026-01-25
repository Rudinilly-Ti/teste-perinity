package com.rudinilly.api.controller;

import com.rudinilly.api.dto.OrderViewDTO;
import com.rudinilly.api.command.order.OrderCommand;
import com.rudinilly.domain.service.OrderService;
import com.rudinilly.domain.model.entity.Order;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/orders")
@Tag(name = "Orders")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @POST
    @Operation(summary = "Create an order")
    @APIResponse(responseCode = "201", description = "Order created")
    public Response create(OrderCommand command) {
        Order order = orderService.create(command);
        return Response.status(Response.Status.CREATED).entity(order).build();
    }

    @GET
    @Operation(summary = "List all orders")
    public Response listOrders() {
        List<OrderViewDTO> orders = orderService.listOrders();

        return Response.ok(orders).build();
    }

    @GET
    @Path("/client/{id}")
    @Operation(summary = "Find client orders by client id")
    public Response findByClientId(@PathParam("id") UUID clientId) {
        List<Order> orders = orderService.findByClientId(clientId);

        return Response.ok(orders).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Find an order by id")
    public Response findById(@PathParam("id") UUID id) {
        Order order = orderService.findById(id);

        return Response.ok(order).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete an order by id")
    public Response delete(@PathParam("id") UUID id) {
        orderService.deleteById(id);

        return Response.ok().build();
    }
}
