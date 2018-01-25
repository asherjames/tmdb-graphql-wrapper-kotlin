package ash.kotlin.graphql.types.tvseason

import graphql.annotations.*

@GraphQLName("TvCrew")
class TvCrewType(id: Int = 0) : TvPersonType(id)
{

  @GraphQLField
  var department = ""

  @GraphQLField
  var job = ""
}
