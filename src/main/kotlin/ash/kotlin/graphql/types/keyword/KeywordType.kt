package ash.kotlin.graphql.types.keyword


import ash.kotlin.graphql.types.TmdbObjectType
import graphql.annotations.GraphQLField

class KeywordType(id: Int = 0, @GraphQLField val name: String = "") : TmdbObjectType(id)