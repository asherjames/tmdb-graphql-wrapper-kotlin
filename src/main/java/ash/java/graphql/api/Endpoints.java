package ash.java.graphql.api;

import ash.java.graphql.TmdbSchema;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Component
@Path("/")
public class Endpoints {

    private final TmdbSchema tmdbSchema;

    @Autowired
    public Endpoints(final TmdbSchema tmdbSchema) {
        this.tmdbSchema = tmdbSchema;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEndpoint() {
        return Response.ok("GET endpoint").build();
    }

    @GET
    @Path("/graphql")
    @Produces(MediaType.APPLICATION_JSON)
    public Response graphqlEndpoint(@QueryParam("query") String query) {
        Object result = tmdbSchema.executeQuery(query);
        String response = new Gson().toJson(result);

        return Response.ok(response).build();
    }
}
