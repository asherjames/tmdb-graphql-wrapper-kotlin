package ash.kotlin.graphql

import ash.kotlin.graphql.fields.FieldProducer
import graphql.GraphQL
import graphql.schema.GraphQLObjectType
import graphql.schema.GraphQLSchema
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Inject

class TmdbSchema @Inject constructor(tmdbFields: List<FieldProducer>)
{
    private var graphQl: GraphQL
    private val log: Logger = LoggerFactory.getLogger(TmdbSchema::class.java)

    init
    {
        val fieldDefinitions = tmdbFields
                .map(FieldProducer::getFieldDefinition)
                .toList()

        val queryType = GraphQLObjectType.newObject()
                .name("QueryType")
                .fields(fieldDefinitions)
                .build()

        val tmdbSchema = GraphQLSchema.newSchema()
                .query(queryType)
                .build()

        graphQl = GraphQL(tmdbSchema)
    }

    fun executeQuery(query: String): Any
    {
        log.info("Executing query $query")
        return graphQl.execute(query)
    }
}