package ash.java.graphql.test.schemas;


import ash.java.graphql.test.TestUtil;
import ash.kotlin.graphql.TmdbSchema;
import ash.kotlin.graphql.data.GenreDao;
import ash.kotlin.graphql.fields.FieldProducer;
import ash.kotlin.graphql.fields.GenreSchema;
import ash.kotlin.graphql.types.genre.GenreType;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GenreQueryTest {

    private static Object resultObjectIdName;
    private static JsonObject resultJsonIdName;
    private static JsonObject resultJsonId;
    private static JsonObject resultJsonName;

    @BeforeClass
    public static void setupResults() {
        TmdbSchema schema = new TmdbSchema(mockFields());

        resultObjectIdName = schema.executeQuery("{genres{id name}}");
        resultJsonIdName = TestUtil.extractData(resultObjectIdName);

        Object resultObjectId = schema.executeQuery("{genres{id}}");
        resultJsonId = TestUtil.extractData(resultObjectId);

        Object resultObjectName = schema.executeQuery("{genres{name}}");
        resultJsonName = TestUtil.extractData(resultObjectName);
    }

    @Test
    public void correctQueryShouldNotReturnNull() {
        assertThat(resultObjectIdName).isNotNull();
    }

    @Test
    public void correctQueryShouldReturnCorrectNumberOfGenres() {
        assertThat(resultJsonIdName.get("genres").getAsJsonArray().size()).isEqualTo(3);
    }

    @Test
    public void correctQueryShouldReturnListWithCorrectValues() {
        assertThat(resultJsonIdName.get("genres").getAsJsonArray().get(0).getAsJsonObject().get("id"))
                .isEqualTo(new JsonPrimitive(28));

        assertThat(resultJsonIdName.get("genres").getAsJsonArray().get(0).getAsJsonObject().get("name"))
                .isEqualTo(new JsonPrimitive("Action"));
    }

    @Test
    public void correctIdQueryReturnsListWithJustIds() {
        assertThat(resultJsonId.get("genres").getAsJsonArray().get(0).getAsJsonObject().get("id"))
                .isEqualTo(new JsonPrimitive(28));

        assertThat(resultJsonId.get("genres").getAsJsonArray().get(0).getAsJsonObject().get("name"))
                .isNull();
    }

    @Test
    public void correctNameQueryReturnsListWithJustNames() {
        assertThat(resultJsonName.get("genres").getAsJsonArray().get(0).getAsJsonObject().get("id"))
                .isNull();

        assertThat(resultJsonName.get("genres").getAsJsonArray().get(0).getAsJsonObject().get("name"))
                .isEqualTo(new JsonPrimitive("Action"));
    }

    private static List<FieldProducer> mockFields() {
        List<GenreType> genres = Arrays.asList(
                new GenreType(28, "Action"),
                new GenreType(35, "Comedy"),
                new GenreType(12, "Fantasy"));

        GenreDao genreDao = mock(GenreDao.class);

        when(genreDao.getAllMovieGenres()).thenReturn(genres);

        List<FieldProducer> fieldProducers = new ArrayList<>();
        fieldProducers.add(new GenreSchema(genreDao));

        return fieldProducers;
    }
}
