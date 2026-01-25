package com.rudinilly.api.controller;

import com.rudinilly.api.command.client.ClientCommand;
import com.rudinilly.domain.service.ClientService;
import com.rudinilly.domain.model.entity.Client;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.List;
import java.util.UUID;

@Path("/clients")
@Tag(name = "Clients")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @POST
    @Operation(summary = "Create a client")
    @APIResponse(responseCode = "201", description = "Client registered")
    public Response create(@Valid ClientCommand command) {
        Client client = clientService.create(command);
        return Response.status(Response.Status.CREATED).entity(client).build();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Find a client by id")
    public Response findById(@PathParam("id") UUID id) {
        Client client = clientService.findById(id);

        return Response.ok(client).build();
    }

    @GET
    @Operation(summary = "List all clients")
    public Response findAll() {
        List<Client> clients = clientService.findAll();

        return Response.ok(clients).build();
    }

    @PUT
    @Path("/{id}")
    @Operation(summary = "Update a client")
    public Response update(@PathParam("id") UUID id, @Valid ClientCommand command) {
        Client client = clientService.update(id, command);

        return Response.ok(client).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete a client by id")
    public Response delete(@PathParam("id") UUID id) {
        clientService.delete(id);

        return Response.ok().build();
    }
}
