package nl.student.application.endpoints;

import nl.student.application.service.ILoginService;
import nl.student.application.service.IPlaylistService;
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
    private IPlaylistService playlist;

    @Inject
    private ILoginService loginService;

    @GET
    @Path("/playlists")
    public Response getAllPlaylists(@QueryParam("token")UUID token){
        if (loginService.validToken(token)){
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
        if (loginService.validToken(token)){
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
        if (loginService.validToken(token)){
            return Response.ok().entity(playlist.editPlaylist(playlistId, playlistDTO)).build();
        }
        else{
            return Response.status(401).build();
        }
    }

    @DELETE
    @Path("/playlists/{id}")
    public Response deleteAPlaylist(@PathParam("id") int playlistId, @QueryParam("token")UUID token){
        if (loginService.validToken(token)){
            return Response.ok().entity(playlist.deleteTrack(playlistId)).build();
        }
        else{
            return  Response.status(401).build();
        }
    }
}
