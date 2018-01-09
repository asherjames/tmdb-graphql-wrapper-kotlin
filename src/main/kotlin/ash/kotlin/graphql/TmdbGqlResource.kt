package ash.kotlin.graphql

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.annotation.RequestMethod.*

@RestController
class TmdbGqlResource(@Autowired private val tmdbSchema: TmdbSchema) {

    @RequestMapping(method = [GET])
    fun getEndpoint(): String
    {
        return "GET endpoint"
    }

    @RequestMapping(method = [GET], value = ["/graphql"], produces = ["application/json"])
    fun graphqlEndpoint(@RequestParam("query") query: String): Any
    {
        return tmdbSchema.executeQuery(query)
    }
}