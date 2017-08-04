package ash.kotlin.graphql.data

import ash.java.graphql.data.TmdbHttpUtils
import ash.java.graphql.data.TmdbUrls
import ash.kotlin.graphql.types.genre.GenreType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.JsonNode
import org.springframework.stereotype.Service


@Service
class GenreDaoImpl : GenreDao {
    override fun getAllMovieGenres(): List<GenreType> {
        val response: HttpResponse<JsonNode> = TmdbHttpUtils.sendRequest(TmdbUrls.TmdbUrl.GENRE_LIST_URL)
        val genresString = response.body.`object`["genres"].toString()

        return Gson().fromJson(genresString, object : TypeToken<List<GenreType>>(){}.type)
    }
}