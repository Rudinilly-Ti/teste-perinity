package com.rudinilly.api.controller;

import com.rudinilly.api.command.seller.SellerCommand;
import com.rudinilly.domain.model.entity.Seller;
import com.rudinilly.domain.service.SellerService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import java.util.UUID;

@Path("/sellers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SellerController {
    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @POST
    @Operation(summary = "Create a seller")
    @APIResponse(responseCode = "201", description = "Seller registered")
    public Response create(SellerCommand command) {
        Seller seller = sellerService.create(command);
        return Response.status(Response.Status.CREATED).entity(seller).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Find a seller by id")
    public Response findById(@PathParam("id") UUID sellerId) {
        Seller seller = sellerService.findById(sellerId);

        return Response.ok(seller).build();
    }
}

