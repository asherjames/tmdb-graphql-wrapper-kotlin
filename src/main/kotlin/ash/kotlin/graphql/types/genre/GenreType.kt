package ash.kotlin.graphql.types

import ash.java.graphql.types.TmdbObjectType
import graphql.annotations.GraphQLField

class GenreType(@GraphQLField val name: String = "") : TmdbObjectType()