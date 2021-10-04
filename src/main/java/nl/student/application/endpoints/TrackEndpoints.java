package nl.student.application.endpoints;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/track")
public class TrackEndpoints {

    @GET
    public Response getAll(){
        return Reponse.OK;
    }
}
