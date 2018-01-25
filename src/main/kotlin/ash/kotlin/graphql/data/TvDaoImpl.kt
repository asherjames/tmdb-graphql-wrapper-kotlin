package ash.kotlin.graphql.data

import ash.kotlin.graphql.types.tvseason.TvSeasonType
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.JsonNode

class TvDaoImpl : TvDao
{
  override fun getTvSeason(tvShowId: Int, seasonNumber: Int): TvSeasonType
  {
    val response: HttpResponse<JsonNode> = TmdbRequest().sendTwoArgRequest(TmdbTwoArgUrl.TV_SEASON_URL,
        tvShowId.toString(),
        seasonNumber.toString())
    val responseJson = response.body.`object`.toString()

    return Gson().fromJson(responseJson, object : TypeToken<TvSeasonType>()
    {}.type)
  }
}