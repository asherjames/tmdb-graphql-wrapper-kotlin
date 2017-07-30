package ash.kotlin.graphql.types.tvseason

import ash.kotlin.graphql.types.TmdbObjectType
import com.google.gson.annotations.SerializedName
import graphql.annotations.*

@GraphQLName("TvSeason")
class TvSeasonType(id: Int = 0) : TmdbObjectType(id) {

    @GraphQLField
    @SerializedName("air_date")
    var airDate = ""

    @GraphQLField
    @JvmSuppressWildcards
    var episodes = listOf<TvEpisodeType>()

    @GraphQLField
    var name = ""

    @GraphQLField
    var overview = ""

    @GraphQLField
    @SerializedName("poster_path")
    var posterPath = ""

    @GraphQLField
    @SerializedName("season_number")
    var seasonNumber = 0
}
