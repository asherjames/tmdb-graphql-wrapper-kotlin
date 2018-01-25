package ash.kotlin.graphql.types.movie

import ash.kotlin.graphql.types.TmdbObjectType
import com.google.gson.annotations.SerializedName
import graphql.annotations.*

@GraphQLName("Movie")
class MovieType(id: Int = 0) : TmdbObjectType(id)
{
  @GraphQLField
  var title = ""

  @GraphQLField
  var popularity = 0.0

  @GraphQLField
  var video = false

  @GraphQLField
  var adult = false

  @GraphQLField
  var overview = ""

  @GraphQLField
  @SerializedName("poster_path")
  var posterPath = ""

  @GraphQLField
  @SerializedName("release_date")
  var releaseDate = ""

  @GraphQLField
  @SerializedName("genre_ids")
  var genreIds = listOf<Int>()

  @GraphQLField
  @SerializedName("original_title")
  var originalTitle = ""

  @GraphQLField
  @SerializedName("original_language")
  var originalLanguage = ""

  @GraphQLField
  @SerializedName("backdrop_path")
  var backdropPath = ""

  @GraphQLField
  @SerializedName("vote_count")
  var voteCount = 0

  @GraphQLField
  @SerializedName("vote_average")
  var voteAverage = 0.0

  @GraphQLField
  @SerializedName("media_type")
  var mediaType = ""
}