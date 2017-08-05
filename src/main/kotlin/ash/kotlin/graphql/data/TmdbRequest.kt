package ash.kotlin.graphql.data

import ash.kotlin.graphql.ApiKey
import com.mashape.unirest.http.HttpResponse
import com.mashape.unirest.http.JsonNode
import com.mashape.unirest.http.Unirest

class TmdbRequest {
    private val apiKey = ApiKey.apiKey

    fun sendRequest(tmdbUrl: TmdbUrl): HttpResponse<JsonNode> {
        return sendTmdbRequest(tmdbUrl.url)
    }

    fun sendArgRequest(tmdbArgUrl: TmdbArgUrl, arg: String): HttpResponse<JsonNode> {
        return sendTmdbRequest(tmdbArgUrl.base + arg + tmdbArgUrl.firstPart)
    }

    fun sendTwoArgRequest(tmdbTwoArgUrl: TmdbTwoArgUrl, firstArg: String, secondArg: String): HttpResponse<JsonNode> {
        return sendTmdbRequest(tmdbTwoArgUrl.base + firstArg + tmdbTwoArgUrl.firstPart + secondArg)
    }

    fun sendQueryParamRequest(tmdbQueryUrl: TmdbQueryUrl, queryParams: Map<String, Any>): HttpResponse<JsonNode> {
        return sendTmdbRequest(tmdbQueryUrl.url, queryParams)
    }

    private fun sendTmdbRequest(url: String): HttpResponse<JsonNode> {
        return Unirest.get(url)
                .queryString("api_key", apiKey)
                .asJson()

    }

    private fun sendTmdbRequest(url: String, queryParams: Map<String, Any>): HttpResponse<JsonNode> {
        return Unirest.get(url)
                .queryString("api_key", apiKey)
                .queryString(queryParams)
                .asJson()
    }
}