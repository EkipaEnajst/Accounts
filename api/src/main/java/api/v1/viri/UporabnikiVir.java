package api.v1.viri;

import org.ekipaenajst.entitete.*;
import org.ekipaenajst.beans.UporabnikiZrno;


import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("uporabniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UporabnikiVir {

    private UporabnikiZrno uporabnikiZrno;

    @GET
    public Response vrniUporabnike(){

        List<Uporabnik> uporabniki = uporabnikiZrno.getUporabniki();

        return Response.status(Response.Status.OK).entity(uporabniki).build();
    }

    @GET
    @Path("{id}")
    public Response vrniUporabnika(@PathParam("id") int id){

        Uporabnik uporabnik = uporabnikiZrno.getUporabnik(id);

        return Response.status(Response.Status.OK).entity(uporabnik).build();
    }

    @GET
    @Path("{firstName}")
    public Response vrniUporabnikaZImenom(String firstName, String lastName){

        Uporabnik uporabnik = uporabnikiZrno.getUporabnikByName(firstName, lastName);

        return Response.status(Response.Status.OK).entity(uporabnik).build();
    }

    // ZA TE TRI NISM ZIHR KAJ VRNIT (bi moral bit ok??????????)
    @PUT
    // @Path(blabla)
    public Response posodobiUporabnika(Uporabnik uporabnik){

        uporabnikiZrno.updateUporabnik(uporabnik);

        return Response.status(Response.Status.OK).entity(uporabnik).build();
    }

    @POST //tale methinks ne rabi patha ipd
    public Response dodajUporabnika(Uporabnik uporabnik){

        uporabnikiZrno.createUporabnik(uporabnik);

        return Response.noContent().build();
    }

    @DELETE
    // @Path(asdf)
    public Response izbrisiUporabnika(Uporabnik uporabnik){

        uporabnikiZrno.deleteUporabnik(uporabnik);

        return Response.noContent().build();
    }
}
