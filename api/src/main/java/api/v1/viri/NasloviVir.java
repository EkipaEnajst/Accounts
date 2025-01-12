package api.v1.viri;

import org.ekipaenajst.entitete.*;
import org.ekipaenajst.beans.NasloviZrno;


import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static javax.ws.rs.core.Response.ok;

@Path("naslovi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class NasloviVir {

    private NasloviZrno nasloviZrno;

    @GET
    public Response vrniNaslove(){

        List<Naslov> naslovi = nasloviZrno.getNaslovi();

        return Response.status(Response.Status.OK).entity(naslovi).build();
    }


    @POST
    @Path("{id}")
    public Response ustvariNaslov(@PathParam("id") Integer id, Naslov naslov){
        return Response.status(Response.Status.OK).build();

    }

}
