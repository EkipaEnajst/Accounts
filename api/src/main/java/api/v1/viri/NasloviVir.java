package api.v1.viri;

import org.ekipaenajst.entitete.*;
import org.ekipaenajst.beans.NasloviZrno;


import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;

@Path("naslovi")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
public class NasloviVir {

    private String frontendURL;

    @PostConstruct
    public void init() {

        Map<String,String> env = System.getenv();

        frontendURL = env.get("FRONTEND_URL");
    }

    private NasloviZrno nasloviZrno;

    @GET
    public Response vrniNaslove(){

        List<Naslov> naslovi = nasloviZrno.getNaslovi();

        return Response.status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", frontendURL)
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .entity(naslovi).build();
    }

}
