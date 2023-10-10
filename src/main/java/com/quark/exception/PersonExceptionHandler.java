package com.quark.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;

@Provider
@Slf4j
public class PersonExceptionHandler implements ExceptionMapper<PersonException> {

    @Override
    public Response toResponse(PersonException e) {
        return Response.serverError().entity(e).build();
    }
}
