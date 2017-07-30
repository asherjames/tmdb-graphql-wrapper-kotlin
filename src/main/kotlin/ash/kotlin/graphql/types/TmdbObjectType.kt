package ash.kotlin.graphql.types

import graphql.annotations.GraphQLAnnotations
import graphql.annotations.GraphQLField
import graphql.schema.GraphQLFieldDefinition
import graphql.schema.GraphQLInterfaceType
import graphql.schema.GraphQLObjectType
import org.slf4j.Logger
import org.slf4j.LoggerFactory

abstract class TmdbObjectType(@GraphQLField val id: Int) {
    protected val log: Logger = LoggerFactory.getLogger(this.javaClass)

    fun getGraphQlType(): GraphQLObjectType {
        return try {
            GraphQLAnnotations.`object`(this.javaClass)
        } catch (e: ReflectiveOperationException) {
            log.error("Exception while trying to produce GraphQL type for class", e)
            GraphQLObjectType("", "", listOf<GraphQLFieldDefinition>(), listOf<GraphQLInterfaceType>())
        }
    }
}