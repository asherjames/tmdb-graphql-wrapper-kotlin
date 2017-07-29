package ash.kotlin.graphql.types.keyword


import ash.kotlin.graphql.types.TmdbObjectType
import graphql.annotations.GraphQLField

data class KeywordType(override val id: Int, @GraphQLField val name: String = "") : TmdbObjectType(id)