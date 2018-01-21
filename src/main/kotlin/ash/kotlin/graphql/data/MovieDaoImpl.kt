package ash.kotlin.graphql.data

import ash.kotlin.graphql.types.keyword.KeywordType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.JsonNode

class MovieDaoImpl : MovieDao
{
    override fun getKeywordsForMovie(movieId: Int): List<KeywordType>
    {
        val response: HttpResponse<JsonNode> = TmdbRequest().sendArgRequest(TmdbArgUrl.MOVIE_KEYWORDS_URL,
                movieId.toString())
        val keywords: String = response.body.`object`["keywords"].toString()

        return Gson().fromJson(keywords, object : TypeToken<List<KeywordType>>()
        {}.type)
    }
}