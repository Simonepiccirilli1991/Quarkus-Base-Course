package com.quark.controller;

import com.quark.model.Person;
import com.quark.model.request.UpdatePersonRequest;
import com.quark.service.PersonService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("api/v1/base")
public class BaseController {

    @Inject
    PersonService personService;


    @GET
    @Path("/get/{surname}")
    public Response getHello(@PathParam("surname") String surname){
        return Response.ok(personService.getPerson(surname)).build();
    }

    @POST
    @Path("/add/person")
    public Response addPerson(Person person){
        return Response.ok(personService.addPerson(person)).build();
    }

    @PATCH
    @Path("update/person")
    public Response updatePerson(UpdatePersonRequest request){
        return Response.ok(personService.updatePerson(request)).build();
    }

    @DELETE
    @Path("delete/person/{surname}")
    public Response deletePerson(@PathParam("surname") String surname){
        return Response.ok(personService.deletePerson(surname)).build();
    }
}
