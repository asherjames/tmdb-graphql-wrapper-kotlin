package ash.java.graphql.data;

import ash.java.graphql.types.tvseason.TvSeasonType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;

@Service
public class TvDaoImpl implements TvDao {

    private Gson gson = new Gson();
    private Type tvSeasonType = new TypeToken<TvSeasonType>(){}.getType();

    @Override
    public TvSeasonType getTvSeason(int tvShowId, int seasonNumber) {
        HttpResponse<JsonNode> response = TmdbHttpUtils.sendRequest(TmdbUrls.TmdbTwoArgUrl.TV_SEASON_URL,
                Integer.toString(tvShowId),
                Integer.toString(seasonNumber));

        String responseJson = response.getBody().getObject().toString();

        return gson.fromJson(responseJson, tvSeasonType);
    }
}
