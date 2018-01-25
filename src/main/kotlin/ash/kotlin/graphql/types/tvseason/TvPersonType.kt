package ash.kotlin.graphql.types.tvseason

import ash.kotlin.graphql.types.TmdbObjectType
import com.google.gson.annotations.SerializedName
import graphql.annotations.GraphQLField

open class TvPersonType(id: Int = 0) : TmdbObjectType(id)
{

  @GraphQLField
  var name = ""

  @GraphQLField
  @SerializedName("credit_id")
  var creditId = ""

  @GraphQLField
  @SerializedName("profile_path")
  var profilePath = ""
}
