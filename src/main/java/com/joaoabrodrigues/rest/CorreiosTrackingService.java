package com.joaoabrodrigues.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.joaoabrodrigues.integration.CorreiosIntegration;

@Path("/track")
public class CorreiosTrackingService {

    @GET
    @Path("/{object}")
    public Response trackingObject(@PathParam("object") String object) {
        try {
            String correiosResponse = CorreiosIntegration.trackObject(object);

            return Response.status(200).entity("It works! -> " + correiosResponse).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).entity("Doesn't work :(").build();
        }
    }

}
