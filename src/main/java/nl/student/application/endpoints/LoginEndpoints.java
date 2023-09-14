package nl.student.application.endpoints;

import nl.student.application.service.ILoginService;
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
    private ILoginService loginService;

    @POST
    public Response login(UserDTO userDTO){
        userDTO = loginService.login(userDTO);
        if(userDTO != null){
            return Response.status(201).entity(userDTO).build();
        }
        else
        {
            return Response.status(401).build();
        }
    }
}
