package ash.kotlin.graphql.types.genre

import ash.java.graphql.types.TmdbObjectType
import graphql.annotations.GraphQLField

class GenreType(@GraphQLField val name: String = "") : TmdbObjectType()