package ash.kotlin.graphql.types.tvseason

import ash.kotlin.graphql.types.TmdbObjectType
import com.google.gson.annotations.SerializedName
import graphql.annotations.*

@GraphQLName("TvEpisode")
open class TvEpisodeType(id: Int = 0) : TmdbObjectType(id)
{

    @GraphQLField
    @SerializedName("air_date")
    var airDate = ""

    @GraphQLField
    var crew = listOf<TvCrewType>()

    @GraphQLField
    @SerializedName("episode_number")
    var episodeNumber = 0

    @GraphQLField
    @SerializedName("guest_stars")
    var guestStars = listOf<TvGuestStarType>()

    @GraphQLField
    var name = ""

    @GraphQLField
    var overview = ""

    @GraphQLField
    @SerializedName("production_code")
    var productionCode = ""

    @GraphQLField
    @SerializedName("season_number")
    var seasonNumber = 0

    @GraphQLField
    @SerializedName("still_path")
    var stillPath = ""

    @GraphQLField
    @SerializedName("vote_average")
    var voteAverage = 0.0

    @GraphQLField
    @SerializedName("vote_count")
    var voteCount = 0
}
