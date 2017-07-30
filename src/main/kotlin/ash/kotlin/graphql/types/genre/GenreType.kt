package ash.kotlin.graphql.types.genre

import ash.kotlin.graphql.types.TmdbObjectType
import graphql.annotations.GraphQLField

class GenreType(id: Int = 0, @GraphQLField val name: String = "") : TmdbObjectType(id)