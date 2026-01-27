package com.quarkus.infrastructure.rest;

import com.quarkus.application.service.CustomerService;
import com.quarkus.application.service.dto.CustomerRequest;
import com.quarkus.application.service.dto.CustomerResponse;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import io.smallrye.mutiny.Uni;

import java.util.List;

@Path("/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

    @Inject
    CustomerService service;

    @POST
    public Uni<Response> create(@Valid CustomerRequest request) {
        return service.create(request)
                .map(r -> Response.status(Response.Status.CREATED).entity(r).build());
    }

    @GET
    public Uni<List<CustomerResponse>> findAll() {
        return service.findAll();
    }

    @GET
    @Path("/{id}")
    public Uni<CustomerResponse> findById(@PathParam("id") Long id) {
        return service.findById(id);
    }

    @PUT
    @Path("/{id}")
    public Uni<CustomerResponse> update(
            @PathParam("id") Long id,
            @Valid CustomerRequest request) {
        return service.update(id, request);
    }

    @DELETE
    @Path("/{id}")
    public Uni<Response> delete(@PathParam("id") Long id) {
        return service.delete(id)
                .replaceWith(Response.noContent().build());
    }
}

