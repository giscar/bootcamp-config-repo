package com.company.interfaces.rest.exception;

import com.company.application.exception.EmailAlreadyRegisteredException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class EmailAlreadyRegisteredExceptionMapper
        implements ExceptionMapper<EmailAlreadyRegisteredException> {

    @Override
    public Response toResponse(EmailAlreadyRegisteredException ex) {
        return Response.status(Response.Status.CONFLICT)
                .entity(new ErrorResponse(ex.getMessage()))
                .build();
    }
}
