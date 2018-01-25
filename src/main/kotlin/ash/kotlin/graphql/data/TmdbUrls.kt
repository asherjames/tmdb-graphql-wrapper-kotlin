package ash.kotlin.graphql.data

private val BASE_URL = "https://api.themoviedb.org/3"

enum class TmdbUrl(val url: String)
{
  GENRE_LIST_URL("$BASE_URL/genre/movie/list")
}

enum class TmdbArgUrl(val base: String, val firstPart: String)
{
  MOVIE_KEYWORDS_URL("$BASE_URL/movie/", "/keywords")
}

enum class TmdbTwoArgUrl(val base: String, val firstPart: String)
{
  TV_SEASON_URL("$BASE_URL/tv/", "/season/")
}

enum class TmdbQueryUrl(val url: String)
{
  MOVIE_SEARCH_URL("$BASE_URL/search/movie"),
  MULTI_SEARCH_URL("$BASE_URL/search/multi")
}