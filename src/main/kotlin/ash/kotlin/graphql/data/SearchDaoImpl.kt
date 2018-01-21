package ash.kotlin.graphql.data

import ash.kotlin.graphql.types.movie.MovieType
import ash.kotlin.graphql.types.multisearch.PersonType
import ash.kotlin.graphql.types.multisearch.TvShowType
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken

class SearchDaoImpl : SearchDao
{

    private val RESULTS = "results"
    private val gson = Gson()
    private val parser = JsonParser()
    private val movieSearchListType = object : TypeToken<List<MovieType>>() {}.type

    override fun searchMoviesWithQuery(query: String): List<MovieType>
    {
        val queryMap: MutableMap<String, Any> = HashMap()
        queryMap.put("query", query)
        val response = TmdbRequest().sendQueryParamRequest(TmdbQueryUrl.MOVIE_SEARCH_URL, queryMap)

        val searchResults = response.body.`object`[RESULTS].toString()

        return gson.fromJson(searchResults, movieSearchListType)
    }

    override fun searchMoviesWithMultipleParameters(params: Map<String, Any>): List<MovieType>
    {
        val response = TmdbRequest().sendQueryParamRequest(TmdbQueryUrl.MOVIE_SEARCH_URL, params)
        val searchResults = response.body.`object`[RESULTS].toString()

        return gson.fromJson(searchResults, movieSearchListType)
    }

    override fun searchMultiSearch(params: Map<String, Any>): List<Any>
    {
        val results: MutableList<Any> = ArrayList()
        val response = TmdbRequest().sendQueryParamRequest(TmdbQueryUrl.MULTI_SEARCH_URL, params)

        val multiSearchResults: JsonArray = parser.parse(response.body.toString()).asJsonObject[RESULTS].asJsonArray

        multiSearchResults.forEach {
            val jsonObject = it.asJsonObject
            val mediaType = jsonObject["media_type"].asString

            when (mediaType)
            {
                "movie" -> results.add(gson.fromJson(it, MovieType::class.java))
                "tv" -> results.add(gson.fromJson(it, TvShowType::class.java))
                "person" -> results.add(gson.fromJson(it, PersonType::class.java))
            }
        }

        return results
    }
}