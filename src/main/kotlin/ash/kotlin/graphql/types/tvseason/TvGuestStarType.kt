package ash.kotlin.graphql.types.tvseason

import graphql.annotations.*

@GraphQLName("TvGuestStar")
class TvGuestStarType(id: Int) : TvPersonType(id)
{

    @GraphQLField
    var character = ""

    @GraphQLField
    var order = 0
}
