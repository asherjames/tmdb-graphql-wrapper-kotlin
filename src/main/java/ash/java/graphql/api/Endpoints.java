package ash.java.graphql.api;

import ash.kotlin.graphql.TmdbSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
public class Endpoints {

    private final TmdbSchema tmdbSchema;

    @Autowired
    public Endpoints(TmdbSchema tmdbSchema) {
        this.tmdbSchema = tmdbSchema;
    }

    @RequestMapping(method = GET)
    public String getEndpoint() {
        return "GET endpoint";
    }

    @RequestMapping(method = GET, value = "/graphql", produces = "application/json")
    public Object graphqlEndpoint(@RequestParam("query") String query) {
        return tmdbSchema.executeQuery(query);
    }
}
