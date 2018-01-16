package ash.kotlin.graphql.resource

import ash.kotlin.graphql.TmdbSchema
import com.codahale.metrics.annotation.Timed
import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.QueryParam

class TmdbGqlResource @Inject constructor(private val tmdbSchema: TmdbSchema)
{

    @GET
    fun pingEndpoint(): String
    {
        return "GET endpoint"
    }

    @GET
    @Path("/graphql")
    @Produces("application/json")
    @Timed
    fun graphqlEndpoint(@QueryParam("query") query: String): Any
    {
        return tmdbSchema.executeQuery(query)
    }
}