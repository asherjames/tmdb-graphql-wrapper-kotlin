package ash.java.graphql.data;

import ash.kotlin.graphql.ApiKey;
import ash.kotlin.graphql.data.TmdbArgUrl;
import ash.kotlin.graphql.data.TmdbQueryUrl;
import ash.kotlin.graphql.data.TmdbTwoArgUrl;
import ash.kotlin.graphql.data.TmdbUrl;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public final class TmdbHttpUtils {

    private static Logger log = LoggerFactory.getLogger(TmdbHttpUtils.class);

    private static final String API_KEY = ApiKey.INSTANCE.getApiKey();

    public static HttpResponse<JsonNode> sendRequest(TmdbUrl tmdbUrl) {
        return sendTmdbRequest(tmdbUrl.getUrl());
    }

    public static HttpResponse<JsonNode> sendRequest(TmdbArgUrl tmdbArgUrl, String arg) {
        return sendTmdbRequest(tmdbArgUrl.getBase() + arg + tmdbArgUrl.getFirstPart());
    }

    public static HttpResponse<JsonNode> sendRequest(TmdbTwoArgUrl tmdbTwoArgUrl, String firstArg, String secondArg) {
        return sendTmdbRequest(tmdbTwoArgUrl.getBase() + firstArg + tmdbTwoArgUrl.getFirstPart() + secondArg);
    }

    public static HttpResponse<JsonNode> sendRequest(TmdbQueryUrl tmdbQueryUrl, Map<String, Object> queryParams) {
        return sendTmdbRequest(tmdbQueryUrl.getUrl(), queryParams);
    }

    private static HttpResponse<JsonNode> sendTmdbRequest(String url) {
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.get(url)
                    .queryString("api_key", API_KEY)
                    .asJson();
        } catch (UnirestException e) {
            log.error("Error while sending request", e);
        }

        return response;
    }

    private static HttpResponse<JsonNode> sendTmdbRequest(String url, Map<String, Object> queryParams) {
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.get(url)
                    .queryString("api_key", API_KEY)
                    .queryString(queryParams)
                    .asJson();
        } catch (UnirestException e) {
            log.error("Error while sending request", e);
        }

        return response;
    }
}
