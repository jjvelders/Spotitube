package nl.student.application.endpoints;

import nl.student.application.service.ITracks;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/")
@Consumes("application/json")
@Produces("application/json")
public class TrackEndpoints {

    @Inject
    private ITracks tracks;

    @GET
    @Path("/track")
    public Response getAll(){
        return Response.ok().entity(tracks.getAllTracks()).build();
    }

    @GET
    @Path("/playlists/{id}/tracks")
    public Response getTracksByPlaylistId(@PathParam("id") int playlistId, @QueryParam("token") UUID token){
        return Response.ok().entity(tracks.getTracksByPlaylistId(playlistId)).build();
    }
}
