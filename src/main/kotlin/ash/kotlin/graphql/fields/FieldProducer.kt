package ash.kotlin.graphql.fields

import graphql.schema.GraphQLFieldDefinition

interface FieldProducer {
    fun getFieldDefinition(): GraphQLFieldDefinition
}