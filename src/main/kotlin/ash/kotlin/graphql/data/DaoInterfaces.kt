package ash.kotlin.graphql.data

import ash.kotlin.graphql.types.genre.GenreType
import ash.kotlin.graphql.types.keyword.KeywordType
import ash.kotlin.graphql.types.movie.MovieType
import ash.kotlin.graphql.types.tvseason.TvSeasonType

interface GenreDao {
    fun getAllMovieGenres(): List<GenreType>
}

interface MovieDao {
    fun getKeywordsForMovie(movieId: Int): List<KeywordType>
}

interface SearchDao {
    fun searchMoviesWithQuery(query: String): List<MovieType>

    @JvmSuppressWildcards
    fun searchMoviesWithMultipleParameters(params: Map<String, Any>): List<MovieType>

    @JvmSuppressWildcards
    fun searchMultiSearch(params: Map<String, Any>): List<Any>
}

interface TvDao {
    fun getTvSeason(tvShowId: Int, seasonNumber: Int): TvSeasonType
}