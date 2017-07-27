package ash.java.graphql.data;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static ash.java.graphql.ApiKeyManager.getKey;
import static ash.java.graphql.data.TmdbUrls.*;

public final class TmdbHttpUtils {

    private static Logger log = LoggerFactory.getLogger(TmdbHttpUtils.class);

    private static final String API_KEY = getKey();

    public static HttpResponse<JsonNode> sendRequest(TmdbUrl tmdbUrl) {
        return sendTmdbRequest(tmdbUrl.url);
    }

    public static HttpResponse<JsonNode> sendRequest(TmdbArgUrl tmdbArgUrl, String arg) {
        return sendTmdbRequest(tmdbArgUrl.base + arg + tmdbArgUrl.firstPart);
    }

    public static HttpResponse<JsonNode> sendRequest(TmdbTwoArgUrl tmdbTwoArgUrl, String firstArg, String secondArg) {
        return sendTmdbRequest(tmdbTwoArgUrl.base + firstArg + tmdbTwoArgUrl.firstPart + secondArg);
    }

    public static HttpResponse<JsonNode> sendRequest(TmdbQueryUrl tmdbQueryUrl, Map<String, Object> queryParams) {
        return sendTmdbRequest(tmdbQueryUrl.url, queryParams);
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
