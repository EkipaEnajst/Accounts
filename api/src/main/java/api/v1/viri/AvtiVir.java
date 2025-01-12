package api.v1.viri;

import org.ekipaenajst.beans.UporabnikiZrno;
import org.ekipaenajst.entitete.*;
import org.ekipaenajst.beans.AvtiZrno;


import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

@Path("avti")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class AvtiVir {

    private String frontendURL;

    @PostConstruct
    public void init() {

        Map<String,String> env = System.getenv();

        frontendURL = env.get("FRONTEND_URL");
    }

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
    @Path("{id}")
    public Response vrniAvte(@PathParam("id") int id){

        List<Avto> avti = avtiZrno.getAvtiByOwner(id);

        return Response.status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", frontendURL)
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .entity(avti).build();
    }

    @POST
    @Path("{id}")
    public Response dodajAvto(@PathParam("id") Integer id){
        avtiZrno.
    }

    @PUT
    public Response posodobiAvto(Avto avto) {
        // TEMPORARY CODE FOR DEBUGGING
        Uporabnik u = uporabnikiZrno.getUporabnik(1000);

        uporabnikiZrno.addAvtoToUporabnik(u,avto);
        avtiZrno.updateAvto(avto);

        return Response.status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", frontendURL)
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .entity(avto).build();
    }

}
