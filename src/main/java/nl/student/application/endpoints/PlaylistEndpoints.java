package nl.student.application.endpoints;

import nl.student.application.service.ILogin;
import nl.student.application.service.IPlaylist;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/playlists")
@Consumes("application/json")
@Produces("application/json")
public class PlaylistEndpoints {

    @Inject
    private IPlaylist playlist;

    @Inject
    private ILogin login;

    @GET
    public Response getAllPlaylists(@QueryParam("token")UUID token){
        if (login.validToken(token)){
            return Response.ok().entity(playlist.getAllPlaylists()).build();
        }
        else{
            return  Response.status(401).build();
        }
    }

    @DELETE
    public Response deleteAPlaylist(@QueryParam("token")UUID token,int id){
        if (login.validToken(token)){
            return Response.ok().entity(playlist.deleteTrack(id)).build();
        }
        else{
            return  Response.status(401).build();
        }
    }
}
