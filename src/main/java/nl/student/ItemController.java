package nl.student;

import nl.student.services.ItemService;
import nl.student.services.dto.ItemDTO;

import javax.ejb.Singleton;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import javax.ws.rs.core.Response;

@Path("/items")
@Singleton
public class ItemController {

    ItemService IS = new ItemService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response itemsInJson() {

        return Response
                .status(Response.Status.OK)
                .entity(IS.getAll())
                .build();
    }

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response item(@PathParam("id") int id) {
        try {
            return Response
                    .status(Response.Status.OK)
                    .entity(IS.getItem(id))
                    .build();
        }
        catch (Exception e){
            return Response
                    .status(Response.Status.NOT_FOUND)
                    .entity("Error!")
                    .build();
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response PostItem(ItemDTO item) {

        IS.addItem(item);

        return Response
                .status(Response.Status.CREATED)
                .build();
    }
}
