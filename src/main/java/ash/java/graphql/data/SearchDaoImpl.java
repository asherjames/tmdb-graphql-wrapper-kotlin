package ash.java.graphql.data;

import ash.java.graphql.types.movie.MovieType;
import ash.java.graphql.types.multisearch.PersonType;
import ash.java.graphql.types.multisearch.TvShowType;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SearchDaoImpl implements SearchDao {

    private static final String RESULTS = "results";
    private Gson gson = new Gson();
    private JsonParser parser = new JsonParser();
    private Type movieSearchListType = new TypeToken<List<MovieType>>(){}.getType();

    @Override
    public List<MovieType> searchMoviesWithQuery(String query) {
        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("query", query);
        HttpResponse<JsonNode> response = TmdbHttpUtils.sendRequest(TmdbUrls.TmdbQueryUrl.MOVIE_SEARCH_URL, queryMap);

        String searchResults = response.getBody().getObject().get(RESULTS).toString();

        return gson.fromJson(searchResults, movieSearchListType);
    }

    @Override
    public List<MovieType> searchMoviesWithMultipleParameters(Map<String, Object> params) {
        HttpResponse<JsonNode> response = TmdbHttpUtils.sendRequest(TmdbUrls.TmdbQueryUrl.MOVIE_SEARCH_URL, params);

        String searchResults = response.getBody().getObject().get(RESULTS).toString();

        return gson.fromJson(searchResults, movieSearchListType);
    }

    @Override
    public List<Object> searchMultiSearch(Map<String, Object> params) {
        List<Object> results = new ArrayList<>();
        HttpResponse<JsonNode> response = TmdbHttpUtils.sendRequest(TmdbUrls.TmdbQueryUrl.MULTI_SEARCH_URL, params);

        JsonArray multiSearchResults = parser.parse(response.getBody().toString())
                .getAsJsonObject()
                .get(RESULTS)
                .getAsJsonArray();

        for (JsonElement element : multiSearchResults) {
            JsonObject jsonObject = element.getAsJsonObject();
            String mediaType = jsonObject.get("media_type").getAsString();

            if (mediaType.equals("movie")) {
                results.add(gson.fromJson(element, MovieType.class));
            } else if (mediaType.equals("tv")) {
                results.add(gson.fromJson(element, TvShowType.class));
            } else if (mediaType.equals("person")) {
                results.add(gson.fromJson(element, PersonType.class));
            }
        }

        return results;
    }
}
