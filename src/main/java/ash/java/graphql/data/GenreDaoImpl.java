package ash.java.graphql.data;

import ash.java.graphql.types.genre.GenreType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class GenreDaoImpl implements GenreDao {

    private Gson gson = new Gson();
    private Type genreListType = new TypeToken<List<GenreType>>(){}.getType();

    @Override
    public List<GenreType> getAllMovieGenres() {
        HttpResponse<JsonNode> response = TmdbHttpUtils.sendRequest(TmdbUrls.TmdbUrl.GENRE_LIST_URL);

        String genresString = response.getBody().getObject().get("genres").toString();

        return gson.fromJson(genresString, genreListType);
    }
}
