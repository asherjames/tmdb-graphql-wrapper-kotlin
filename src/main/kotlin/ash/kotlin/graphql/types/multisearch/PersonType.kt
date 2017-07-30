package ash.kotlin.graphql.types.multisearch

import ash.kotlin.graphql.types.TmdbObjectType
import com.google.gson.annotations.SerializedName
import graphql.annotations.*

@GraphQLName("Person")
class PersonType(id:Int = 0) : TmdbObjectType(id) {
    @GraphQLField
    var adult = false

    @GraphQLField
    var name = ""

    @GraphQLField
    var popularity = 0.0

    @GraphQLField
    @SerializedName("profile_path")
    var profilePath = ""

    @GraphQLField
    @SerializedName("media_type")
    var mediaType = ""
}