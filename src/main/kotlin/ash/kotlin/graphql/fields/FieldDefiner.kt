package ash.kotlin.graphql.fields

import graphql.schema.GraphQLFieldDefinition

interface FieldDefiner
{
  fun getFieldDefinition(): GraphQLFieldDefinition
}