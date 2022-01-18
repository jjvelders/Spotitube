package nl.student.application.endpoints;

import nl.student.application.service.ILogin;
import nl.student.services.domain.dto.UserDTO;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/login")
@Consumes("application/json")
@Produces("application/json")
public class LoginEndpoints {

    @Inject
    private ILogin login;

    @POST
    public Response login(UserDTO dto){
        dto = login.login(dto);
        if(dto != null){
            return Response.ok().entity(dto).build();
        }
        else
        {
            return Response.status(401).build();
        }
    }
}
