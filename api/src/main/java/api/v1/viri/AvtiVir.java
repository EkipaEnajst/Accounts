package api.v1.viri;

import org.ekipaenajst.beans.UporabnikiZrno;
import org.ekipaenajst.entitete.*;
import org.ekipaenajst.beans.AvtiZrno;


import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import javax.inject.Inject;

@Path("avti")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class AvtiVir {

    @Inject
    private AvtiZrno avtiZrno;

    @Inject
    private UporabnikiZrno uporabnikiZrno;

    @GET
    public Response vrniAvte(){

        List<Avto> avti = avtiZrno.getAvti();

        return Response.status(Response.Status.OK).entity(avti).build();
    }

    @GET
    @Path("lastnik/{id}")
    public Response vrniAvtePoLastniku(@PathParam("id") Integer ownerId){

        List<Avto> avti = avtiZrno.getAvti();

        return Response.status(Response.Status.OK).entity(avti).build();
    }

    @PUT
    public Response posodobiAvto(Avto avto) {
        // TEMPORARY CODE FOR DEBUGGING
        Uporabnik u = uporabnikiZrno.getUporabnik(1000);

        uporabnikiZrno.addAvtoToUporabnik(u,avto);
        avtiZrno.updateAvto(avto);

        return Response.status(Response.Status.OK).entity(avto).build();
    }

    @POST
    @Path("{id}")
    public Response ustvariAvto(@PathParam("id") Integer ownerId, Avto avto) {
        avtiZrno.createAvto(avto);

        Uporabnik u = uporabnikiZrno.getUporabnik(ownerId);
        uporabnikiZrno.addAvtoToUporabnik(u,avto);

        return Response.status(Response.Status.OK).entity(avto).build();

    }

}
