package ash.java.graphql.test.schemas;

import ash.java.graphql.TmdbSchema;
import ash.java.graphql.data.MovieDao;
import ash.java.graphql.fields.FieldProducer;
import ash.java.graphql.fields.KeywordSchema;
import ash.java.graphql.test.TestUtil;
import ash.kotlin.graphql.types.keyword.KeywordType;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KeywordQueryTest {

    private static Object resultObject;
    private static JsonObject resultJson;
    private static JsonObject resultIdJson;
    private static JsonObject resultNameJson;

    @BeforeClass
    public static void setupResults() {
        TmdbSchema schema = new TmdbSchema(mockFields());

        resultObject = schema.executeQuery("{keywordList(filmId: 123){id name}}");
        resultJson = TestUtil.extractData(resultObject);

        Object resultIdObject = schema.executeQuery("{keywordList(filmId: 123){id}}");
        resultIdJson = TestUtil.extractData(resultIdObject);

        Object resultNameObject = schema.executeQuery("{keywordList(filmId: 123){name}}");
        resultNameJson = TestUtil.extractData(resultNameObject);
    }

    @Test
    public void correctQueryShouldNotReturnNull() {
        assertThat(resultObject).isNotNull();
    }

    @Test
    public void correctQueryShouldReturnJsonArrayOfCorrectLength(){
        assertThat(resultJson.get("keywordList").getAsJsonArray().size()).isEqualTo(4);
    }

    @Test
    public void correctQueryShouldReturnListWithCorrectKeywords(){
        assertThat(resultJson.get("keywordList").getAsJsonArray().get(0).getAsJsonObject().get("name"))
                .isEqualTo(new JsonPrimitive("elves"));

        assertThat(resultJson.get("keywordList").getAsJsonArray().get(0).getAsJsonObject().get("id"))
                .isEqualTo(new JsonPrimitive(603));
    }

    @Test
    public void correctIdQueryShouldReturnListWithJustIds(){
        assertThat(resultIdJson.get("keywordList").getAsJsonArray().get(3).getAsJsonObject().get("id"))
                .isEqualTo(new JsonPrimitive(10364));

        assertThat(resultIdJson.get("keywordList").getAsJsonArray().get(0).getAsJsonObject().get("name"))
                .isNull();

    }

    @Test
    public void correctNameQueryReturnsListWithJustNames() {
        assertThat(resultNameJson.get("keywordList").getAsJsonArray().get(0).getAsJsonObject().get("id"))
                .isNull();

        assertThat(resultNameJson.get("keywordList").getAsJsonArray().get(2).getAsJsonObject().get("name"))
                .isEqualTo(new JsonPrimitive("hobbit"));
    }

    private static List<FieldProducer> mockFields() {
        List<KeywordType> keywords = new ArrayList<>();
        KeywordType k1 = new KeywordType("elves");
        KeywordType k2 = new KeywordType("dwarves");
        KeywordType k3 = new KeywordType("hobbit");
        KeywordType k4 = new KeywordType("mission");

        k1.setId(603);
        k2.setId(604);
        k3.setId(611);
        k4.setId(10364);

        keywords.add(k1);
        keywords.add(k2);
        keywords.add(k3);
        keywords.add(k4);

        MovieDao movieDao = mock(MovieDao.class);

        when(movieDao.getKeywordsForMovie(123)).thenReturn(keywords);

        List<FieldProducer> fieldProducers = new ArrayList<>();
        fieldProducers.add(new KeywordSchema(movieDao));

        return fieldProducers;
    }
}
