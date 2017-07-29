package ash.kotlin.graphql.types.keyword

import ash.java.graphql.types.TmdbObjectType
import graphql.annotations.GraphQLField

data class KeywordType(@GraphQLField val name: String = "") : TmdbObjectType()