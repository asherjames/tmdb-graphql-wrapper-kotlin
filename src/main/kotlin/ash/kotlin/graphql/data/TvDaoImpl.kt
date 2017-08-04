package ash.kotlin.graphql.data

import ash.java.graphql.data.TmdbHttpUtils
import ash.java.graphql.data.TmdbUrls
import ash.kotlin.graphql.types.tvseason.TvSeasonType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.JsonNode
import org.springframework.stereotype.Service

@Service
class TvDaoImpl : TvDao {
    override fun getTvSeason(tvShowId: Int, seasonNumber: Int): TvSeasonType {
        val response: HttpResponse<JsonNode> = TmdbHttpUtils.sendRequest(TmdbUrls.TmdbTwoArgUrl.TV_SEASON_URL,
                tvShowId.toString(),
                seasonNumber.toString())
        val responseJson = response.body.`object`.toString()

        return Gson().fromJson(responseJson, object : TypeToken<TvSeasonType>(){}.type)
    }
}