package ash.kotlin.graphql.data

import ash.kotlin.graphql.AppConfig
import ash.kotlin.graphql.TmdbGqlException
import ash.kotlin.graphql.types.genre.GenreType
import ash.kotlin.graphql.types.keyword.KeywordType
import ash.kotlin.graphql.types.movie.MovieType
import ash.kotlin.graphql.types.multisearch.PersonType
import ash.kotlin.graphql.types.multisearch.TvShowType
import ash.kotlin.graphql.types.tvseason.TvSeasonType
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.google.gson.Gson
import com.google.gson.JsonParser
import org.jvnet.hk2.annotations.Service
import javax.inject.Inject

@Service
class TmdbUtilImpl @Inject constructor(config: AppConfig) : TmdbUtil
{
    init
    {
        FuelManager.instance.basePath = config.baseUrl
    }

    override fun getGenreList(): List<GenreType>
    {
        return sendListRequest("/genre/movie/list")
    }

    override fun getMovieKeywords(movieId: Int): List<KeywordType>
    {
        return sendListRequest("/movie/$movieId/keywords")
    }

    override fun getTvSeason(tvShowId: Int, seasonNumber: Int): TvSeasonType
    {
        return sendRequest("/tv/$tvShowId/season/$seasonNumber") ?: TvSeasonType()
    }

    override fun searchMoviesWithQuery(query: String): List<MovieType>
    {
        return sendListRequest("/search/movie", listOf(query to "query"))
    }

    override fun searchMoviesWithMultipleParameters(params: List<Pair<String, Any?>>): List<MovieType>
    {
        return sendListRequest("/search/movie", params)
    }

    override fun searchMulti(params: List<Pair<String, Any?>>): List<Any>
    {
        var data = emptyList<Any>()

        "/search/multi".httpGet(params).responseObject(MultiDeserializer()) {_, _, result ->
            when (result)
            {
                is Result.Failure -> throw TmdbGqlException(result.error)
                is Result.Success -> data = result.get()
            }
        }

        return data
    }

    private class MultiDeserializer : ResponseDeserializable<List<Any>>
    {
        override fun deserialize(content: String): List<Any>?
        {
            val gson = Gson()
            val objectList = mutableListOf<Any>()

            val responseJson = JsonParser().parse(content).asJsonObject["results"].asJsonArray

            responseJson.forEach {
                val jsonObject = it.asJsonObject
                val mediaType = jsonObject["media_type"].asString

                when (mediaType)
                {
                    "movie" -> objectList.add(gson.fromJson(it, MovieType::class.java))
                    "tv" -> objectList.add(gson.fromJson(it, TvShowType::class.java))
                    "person" -> objectList.add(gson.fromJson(it, PersonType::class.java))
                }
            }

            return objectList
        }
    }

    private inline fun <reified T : Any> sendRequest(url: String): T?
    {
        var data: T? = null

        url.httpGet().responseObject<T> {_, _, result ->
            when (result)
            {
                is Result.Failure -> throw TmdbGqlException(result.error)
                is Result.Success -> data = result.get()
            }
        }

        return data
    }

    private fun <T : Any> sendListRequest(url: String, params: List<Pair<String, Any?>> = emptyList()): List<T>
    {
        var data = emptyList<T>()

        url.httpGet(params).responseObject<List<T>> {_, _, result ->
            when (result)
            {
                is Result.Failure -> throw TmdbGqlException(result.error)
                is Result.Success -> data = result.get()
            }
        }

        return data
    }
}