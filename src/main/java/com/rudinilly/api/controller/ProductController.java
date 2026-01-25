package com.rudinilly.api.controller;

import com.rudinilly.api.command.product.ProductCommand;
import com.rudinilly.domain.service.ProductService;
import com.rudinilly.domain.model.entity.Product;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.List;
import java.util.UUID;

@Path("/products")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @POST
    @Operation(summary = "Create a product")
    @APIResponse(responseCode = "201", description = "Product created")
    public Response create(@Valid ProductCommand command) {
        Product product = productService.create(command);

        return Response.status(Response.Status.CREATED).entity(product).build();
    }

    @GET
    @Operation(summary = "Find a product by id")
    @Path("/{id}")
    public Response findById(@PathParam("id") UUID id) {
        Product product = productService.findById(id);

        return Response.ok(product).build();
    }

    @GET
    @Operation(summary = "Find all products ordered by name")
    public Response findAll() {
        List<Product> products = productService.findAll();
        return Response.ok(products).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update a product")
    public Response update(@PathParam("id") UUID id, @Valid ProductCommand command) {
        Product product = productService.update(id, command);

        return Response.ok(product).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a product by id")
    public Response delete(@PathParam("id") UUID id) {
        productService.delete(id);

        return Response.ok().build();
    }
}
