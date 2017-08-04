package ash.kotlin.graphql.data

import ash.java.graphql.data.TmdbHttpUtils
import ash.kotlin.graphql.types.keyword.KeywordType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.JsonNode
import org.springframework.stereotype.Service

@Service
class MovieDaoImpl : MovieDao {
    override fun getKeywordsForMovie(movieId: Int): List<KeywordType> {
        val response: HttpResponse<JsonNode> = TmdbHttpUtils.sendRequest(TmdbArgUrl.MOVIE_KEYWORDS_URL,
                movieId.toString())
        val keywords: String = response.body.`object`["keywords"].toString()

        return Gson().fromJson(keywords, object : TypeToken<List<KeywordType>>(){}.type)
    }
}