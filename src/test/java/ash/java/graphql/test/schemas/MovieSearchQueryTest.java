package ash.java.graphql.test.schemas;

import ash.java.graphql.TmdbSchema;
import ash.java.graphql.data.SearchDao;
import ash.java.graphql.types.movie.MovieType;
import ash.java.graphql.fields.FieldProducer;
import ash.java.graphql.fields.MovieSearchSchema;
import ash.java.graphql.test.TestUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MovieSearchQueryTest {

    private static Object posterPathResultObject;
    private static JsonObject posterPathResultJson;
    private static JsonObject multipleFieldsResultJson;
    private static JsonArray nullQueryResultJson;

    @BeforeClass
    public static void setupResults() {
        TmdbSchema schema = new TmdbSchema(mockFields());

        posterPathResultObject = schema.executeQuery("{movieSearch(query: \"Das Boot\"){posterPath}}");
        posterPathResultJson = TestUtil.extractData(posterPathResultObject);

        Object multipleFieldsResultObject = schema.executeQuery("{movieSearch(query: \"Das Boot\"){releaseDate title popularity voteCount}}");
        multipleFieldsResultJson = TestUtil.extractData(multipleFieldsResultObject);

        Object nullQueryResultObject = schema.executeQuery("{movieSearch(year: 1981){releaseDate title popularity voteCount}}");
        nullQueryResultJson = TestUtil.extractError(nullQueryResultObject);
    }

    @Test
    public void correctPosterpathQueryShouldNotReturnNull() {
        assertThat(posterPathResultObject).isNotNull();
    }

    @Test
    public void correctPosterpathQueryShouldReturnCorrectValue() {
        assertThat(posterPathResultJson.get("movieSearch").getAsJsonArray().get(0).getAsJsonObject().get("posterPath"))
                .isEqualTo(new JsonPrimitive("/kI1rptTkqDWj6SBRsYwguBvPViT.jpg"));
    }

    @Test
    public void correctMultipleFieldQueryReturnsAllFields() {
        JsonObject queryObject = multipleFieldsResultJson.get("movieSearch").getAsJsonArray().get(0).getAsJsonObject();

        assertThat(queryObject.get("releaseDate")).isEqualTo(new JsonPrimitive("1981-09-16"));
        assertThat(queryObject.get("title")).isEqualTo(new JsonPrimitive("Das Boot"));
        assertThat(queryObject.get("popularity")).isEqualTo(new JsonPrimitive(3.495501));
        assertThat(queryObject.get("voteCount")).isEqualTo(new JsonPrimitive(501));
    }

    @Test
    public void nullQueryReturnsError() {
        JsonObject errorObject = nullQueryResultJson.get(0).getAsJsonObject();

        assertThat(errorObject.get("description").getAsString()).isEqualTo("Missing field argument query");
    }

    private static List<FieldProducer> mockFields() {
        List<MovieType> movies = new ArrayList<>();

        movies.add(TestTypeInstances.getMovie());

        SearchDao searchDao = mock(SearchDao.class);

        Map<String, Object> params = new HashMap<>();
        params.put("query", "Das Boot");
        params.put("language", null);
        params.put("page", null);
        params.put("includeAdult", null);
        params.put("region", null);
        params.put("year", null);
        params.put("primaryReleaseYear", null);

        when(searchDao.searchMoviesWithMultipleParameters(params)).thenReturn(movies);

        List<FieldProducer> fieldProducers = new ArrayList<>();
        fieldProducers.add(new MovieSearchSchema(searchDao));

        return fieldProducers;
    }
}
