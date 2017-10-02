package ash.kotlin.graphql

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.*

@RestController
class TmdbGqlResource(@Autowired private val tmdbSchema: TmdbSchema) {

    @RequestMapping(method = arrayOf(GET))
    fun getEndpoint(): String
    {
        return "GET endpoint"
    }

    @RequestMapping(method = arrayOf(GET), value = "/graphql", produces = arrayOf("application/json"))
    fun graphqlEndpoint(@RequestParam("query") query: String): Any
    {
        return tmdbSchema.executeQuery(query)
    }
}