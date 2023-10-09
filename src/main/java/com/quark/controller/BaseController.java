package com.quark.controller;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("api/v1/base")
public class BaseController {


    @GET
    @Path("/get/{name}")
    public Response getHello(@PathParam("name") String name){
        return Response.ok("Hello "+name).build();
    }
}
