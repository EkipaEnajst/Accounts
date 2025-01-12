package api.v1.viri;

import com.kumuluz.ee.rest.beans.QueryParameters;
import org.ekipaenajst.entitete.*;
import org.ekipaenajst.beans.UporabnikiZrno;


import javax.annotation.PostConstruct;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Map;

@Path("uporabniki")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class UporabnikiVir {

    private String frontendURL;

    @PostConstruct
    public void init() {

        Map<String,String> env = System.getenv();

        //frontendURL = env.get("FRONTEND_DEPLOYMENT_SERVICE_SERVICE_HOST");
        frontendURL="*";
    }

    @Inject
    private UporabnikiZrno uporabnikiZrno;

    @Context
    protected UriInfo uriInfo;

    @GET
    public Response vrniUporabnike(){

        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<Uporabnik> uporabniki = uporabnikiZrno.getUporabniki(query);

        //List<Uporabnik> uporabniki = uporabnikiZrno.getUporabniki();

        return Response.status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", frontendURL)
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .entity(uporabniki).build();
    }

    @GET
    @Path("{id}")
    public Response vrniUporabnika(@PathParam("id") int id){

        Uporabnik uporabnik = uporabnikiZrno.getUporabnik(id);

        return Response.status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", frontendURL)
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .entity(uporabnik).build();
    }

    @POST
    public Response dodajUporabnika(Uporabnik uporabnik){

        if (uporabnikiZrno.createUporabnik(uporabnik)==null) {
            return Response.status(Response.Status.FOUND).header("Access-Control-Allow-Origin", frontendURL)
                    .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();
        }

        return Response.status(Response.Status.CREATED)
                .header("Access-Control-Allow-Origin", frontendURL)
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .entity(uporabnik).build();
    }

    @POST
    @Path("auth")
    public Response preveriUporabnika(Uporabnik uporabnik) {
        Uporabnik u = uporabnikiZrno.getUporabnikByEmailAndPassword(uporabnik);

        if (u==null){ return Response.status(Response.Status.UNAUTHORIZED).header("Access-Control-Allow-Origin", frontendURL)
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT").build();}

        return Response.status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", frontendURL)
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .entity(u).build();
    }
//    @GET
//    @Path("{firstName}")
//    public Response vrniUporabnikaZImenom(String firstName, String lastName){
//
//        Uporabnik uporabnik = uporabnikiZrno.getUporabnikByName(firstName, lastName);
//
//        return Response.status(Response.Status.OK).entity(uporabnik).build();
//    }

    // ZA TE TRI NISM ZIHR KAJ VRNIT (bi moral bit ok??????????)
    @PUT
    // @Path(blabla)
    public Response posodobiUporabnika(Uporabnik uporabnik){

        uporabnikiZrno.updateUporabnik(uporabnik);

        return Response.status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", frontendURL)
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .entity(uporabnik).build();
    }


    @DELETE
    @Path("{id}")
    public Response izbrisiUporabnika(@PathParam("id") int id){

        Uporabnik uporabnik = uporabnikiZrno.getUporabnik(id);

        uporabnikiZrno.deleteUporabnik(uporabnik);

        return Response.noContent()
                .header("Access-Control-Allow-Origin", frontendURL)
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .build();
    }
}
