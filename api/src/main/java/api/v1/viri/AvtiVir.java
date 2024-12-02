package api.v1.viri;

import org.ekipaenajst.entitete.*;
import org.ekipaenajst.beans.AvtiZrno;


import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("avti")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class AvtiVir {

    private AvtiZrno avtiZrno;

    @GET
    public Response vrniAvte(){

        List<Avto> avti = avtiZrno.getAvti();

        return Response.status(Response.Status.OK).entity(avti).build();
    }

}
