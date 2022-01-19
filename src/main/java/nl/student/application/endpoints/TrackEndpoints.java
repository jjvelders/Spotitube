package nl.student.application.endpoints;

import nl.student.application.service.ILoginService;
import nl.student.application.service.ITracksService;
import nl.student.services.domain.dto.TrackDTO;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Path("/")
@Consumes("application/json")
@Produces("application/json")
public class TrackEndpoints {

    @Inject
    private ITracksService tracksService;

    @Inject
    private ILoginService loginService;

    @GET
    @Path("/tracks")
    public Response getAvailableTracksByPlaylistId(@QueryParam("forPlaylist") int playlistId, @QueryParam("token") UUID token){
        if (loginService.validToken(token)){
            return Response.ok().entity(tracksService.getAvailableTracksByPlaylistId(playlistId)).build();
        }
        else{
            return Response.status(401).build();
        }
    }

    @GET
    @Path("/playlists/{id}/tracks")
    public Response getTracksByPlaylistId(@PathParam("id") int playlistId, @QueryParam("token") UUID token){
        if (loginService.validToken(token)){
            return Response.ok().entity(tracksService.getTracksByPlaylistId(playlistId)).build();
        }
        else{
            return Response.status(401).build();
        }
    }

    @POST
    @Path("/playlists/{id}/tracks")
    @Consumes("application/json")
    public Response postAddTrackToPlaylist(@PathParam("id") int playlistId, @QueryParam("token") UUID token, TrackDTO trackDTO){
        if (loginService.validToken(token)){
            return Response.ok().entity(tracksService.addTrackToPlaylist(trackDTO.getId(), playlistId)).build();
        }
        else{
            return Response.status(401).build();
        }
    }

    @DELETE
    @Path("/playlists/{playlistId}/tracks/{trackId}")
    @Consumes("application/json")
    public Response deleteTrackFromPlaylist(@PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId, @QueryParam("token") UUID token){
        if (loginService.validToken(token)){
            return Response.ok().entity(tracksService.deleteTrackFromPlaylist(playlistId, trackId)).build();
        }
        else{
            return Response.status(401).build();
        }
    }
}
