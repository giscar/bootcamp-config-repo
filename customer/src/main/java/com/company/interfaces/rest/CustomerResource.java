package com.company.interfaces.rest;

import com.company.application.dto.CustomerRequest;
import com.company.application.service.CustomerService;
import io.smallrye.mutiny.Uni;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/customers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CustomerResource {

    private final CustomerService service;

    public CustomerResource(CustomerService service) {
        this.service = service;
    }

    @POST
    public Uni<Response> register(CustomerRequest request) {
        return service.register(request)
                .map(r -> Response.status(Response.Status.CREATED).entity(r).build());
    }
}
