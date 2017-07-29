package ash.kotlin.graphql.types.multisearch

import ash.java.graphql.types.TmdbObjectType
import com.google.gson.annotations.SerializedName
import graphql.annotations.GraphQLField
import graphql.annotations.GraphQLName

@GraphQLName("TvShow")
class TvShowType : TmdbObjectType() {

    @GraphQLField
    var popularity = 0.0

    @GraphQLField
    var overview = ""

    @GraphQLField
    var name = ""

    @GraphQLField
    @SerializedName("poster_path")
    var posterPath = ""

    @GraphQLField
    @SerializedName("backdrop_path")
    var backdropPath = ""

    @GraphQLField
    @SerializedName("vote_average")
    var voteAverage = ""

    @GraphQLField
    @SerializedName("first_air_date")
    var firstAirDate = ""

    @GraphQLField
    @SerializedName("origin_country")
    var originCountry = listOf<String>()

    @GraphQLField
    @SerializedName("genre_ids")
    var genreIds = listOf<Int>()

    @GraphQLField
    @SerializedName("original_language")
    var originalLanguage = ""

    @GraphQLField
    @SerializedName("vote_count")
    var voteCount = 0

    @GraphQLField
    @SerializedName("original_name")
    var originalName = ""
}