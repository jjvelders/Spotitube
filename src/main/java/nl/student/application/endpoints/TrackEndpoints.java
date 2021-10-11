package nl.student.application.endpoints;

import nl.student.application.service.ITracks;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/track")
@Consumes("application/json")
@Produces("application/json")
public class TrackEndpoints {

    @Inject
    private ITracks tracks;

    @GET
    public Response getAll(){
        return Response.ok().entity(tracks.getAllTracks()).build();
    }
}
