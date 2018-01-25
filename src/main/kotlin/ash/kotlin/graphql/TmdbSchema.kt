package ash.kotlin.graphql

import ash.kotlin.graphql.fields.FieldDefiner
import graphql.GraphQL
import graphql.schema.GraphQLObjectType
import graphql.schema.GraphQLSchema
import org.jvnet.hk2.annotations.Service
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.inject.Inject

@Service
class TmdbSchema(@Inject private val tmdbFields: Iterable<FieldDefiner>)
{
  private var graphQl: GraphQL
  private val log: Logger = LoggerFactory.getLogger(TmdbSchema::class.java)

  init
  {
    val fieldDefinitions = tmdbFields
        .map(FieldDefiner::getFieldDefinition)
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