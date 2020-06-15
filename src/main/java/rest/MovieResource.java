package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import facades.MovieFacade;
import java.io.IOException;
import java.sql.SQLException;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import utils.EMF_Creator;

@Path("movie-info")
public class MovieResource {

    private static EntityManagerFactory EMF
            = EMF_Creator.createEntityManagerFactory(EMF_Creator.DbSelector.DEV, EMF_Creator.Strategy.CREATE);
    private static final MovieFacade FACADE = MovieFacade.getFacade(EMF); 
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"Hello World\"}";
    }

    @GET
    @Path("{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByTitle(@PathParam("title") String title) throws IOException {
        String movie = GSON.toJson(FACADE.getMovieByTitle(title));
        return Response.ok(movie).build();
    }
    
    @GET
    @Path("/imdb/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({"user"})
    public Response getImdbByTitle(@PathParam("title") String title) throws IOException {
        String movie = GSON.toJson(FACADE.getMovieWithImdbByTitle(title));
        return Response.ok(movie).build();
    }

}
