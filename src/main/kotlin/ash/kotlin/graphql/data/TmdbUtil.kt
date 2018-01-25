package ash.kotlin.graphql.data

import ash.kotlin.graphql.types.genre.GenreType
import ash.kotlin.graphql.types.keyword.KeywordType
import ash.kotlin.graphql.types.movie.MovieType
import ash.kotlin.graphql.types.tvseason.TvSeasonType
import org.jvnet.hk2.annotations.Contract

@Contract
interface TmdbUtil
{
  fun getGenreList(): List<GenreType>
  fun getMovieKeywords(movieId: Int): List<KeywordType>
  fun getTvSeason(tvShowId: Int, seasonNumber: Int): TvSeasonType
  fun searchMoviesWithQuery(query: String): List<MovieType>
  fun searchMoviesWithMultipleParameters(params: List<Pair<String, Any?>>): List<MovieType>
  fun searchMulti(params: List<Pair<String, Any?>>): List<Any>
}