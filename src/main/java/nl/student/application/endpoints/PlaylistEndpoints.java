package nl.student.application.endpoints;

import nl.student.application.service.ILogin;
import nl.student.application.service.IPlaylist;
import nl.student.services.domain.dto.PlaylistDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/")
@Consumes("application/json")
@Produces("application/json")
public class PlaylistEndpoints {

    @Inject
    private IPlaylist playlist;

    @Inject
    private ILogin login;

    @GET
    @Path("/playlists")
    public Response getAllPlaylists(@QueryParam("token")UUID token){
        if (login.validToken(token)){
            return Response.ok().entity(playlist.getAllPlaylists()).build();
        }
        else{
            return  Response.status(401).build();
        }
    }

    @POST
    @Path("/playlists")
    @Consumes("application/json")
    public Response postNewPlaylist(@QueryParam("token")UUID token, PlaylistDTO playlistDTO){
        if (login.validToken(token)){
            return Response.ok().entity(playlist.addPlaylist(playlistDTO, token)).build();
        }
        else{
            return Response.status(401).build();
        }
    }

    @PUT
    @Path("/playlists/{id}")
    @Consumes("application/json")
    public Response putEditPlaylist(@PathParam("id") int playlistId, @QueryParam("token")UUID token, PlaylistDTO playlistDTO){
        if (login.validToken(token)){
            return Response.ok().entity(playlist.editPlaylist(playlistId, playlistDTO)).build();
        }
        else{
            return Response.status(401).build();
        }
    }

    @DELETE
    @Path("/playlists/{id}")
    public Response deleteAPlaylist(@PathParam("id") int id, @QueryParam("token")UUID token){
        if (login.validToken(token)){
            return Response.ok().entity(playlist.deleteTrack(id)).build();
        }
        else{
            return  Response.status(401).build();
        }
    }
}
