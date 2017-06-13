package com.joaoabrodrigues.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONObject;
import org.json.XML;

import com.joaoabrodrigues.integration.CorreiosIntegration;

@Path("/track")
public class CorreiosTrackingService {

    private static final int PRETTY_PRINT_INDENT_FACTOR = 4;

	@GET
    @Path("/{object}")
    @Produces("application/json")
    public Response trackingObject(@PathParam("object") String object) {
        try {
            JSONObject json = XML.toJSONObject(CorreiosIntegration.trackObject(object));
            String prettyJson = json.toString(PRETTY_PRINT_INDENT_FACTOR);
            
            return Response.status(200).entity(prettyJson).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(500).entity(String.format("Didn't work :( \n Error: %s ", e.getMessage())).build();
        }
    }

}
