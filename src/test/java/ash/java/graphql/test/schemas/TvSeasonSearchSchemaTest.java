package ash.java.graphql.test.schemas;

import ash.java.graphql.TmdbSchema;
import ash.java.graphql.data.TvDao;
import ash.java.graphql.fields.FieldProducer;
import ash.java.graphql.fields.TvSeasonSearchSchema;
import ash.java.graphql.test.TestUtil;
import ash.java.graphql.types.tvseason.TvCrewType;
import ash.java.graphql.types.tvseason.TvEpisodeType;
import ash.java.graphql.types.tvseason.TvGuestStarType;
import com.google.gson.*;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TvSeasonSearchSchemaTest {

    private static Gson gson = new Gson();

    private static JsonObject seasonIdJson;
    private static JsonObject episodeNameJson;
    private static JsonObject crewJobJson;
    private static JsonObject guestNameCrewIdJson;

    @BeforeClass
    public static void setupResults() {
        TmdbSchema schema = new TmdbSchema(mockFields());

        Object seasonIdQueryResultObject = schema.executeQuery("{tvSeasonSearch(tvId: 1408, seasonNum: 5){id}}");
        seasonIdJson = TestUtil.extractData(seasonIdQueryResultObject);

        Object episodeNameQueryResultObject = schema.executeQuery("{tvSeasonSearch(tvId: 1408, seasonNum: 5){episodes{name}}}");
        episodeNameJson = TestUtil.extractData(episodeNameQueryResultObject);

        Object crewJobQueryResultObject = schema.executeQuery("{tvSeasonSearch(tvId: 1408, seasonNum: 5){episodes{crew{job}}}}");
        crewJobJson = TestUtil.extractData(crewJobQueryResultObject);

        Object guestNameAndCrewIdQueryResultObject = schema.executeQuery("{tvSeasonSearch(tvId: 1408, seasonNum: 5){episodes{crew{id} guestStars{name}}}}");
        guestNameCrewIdJson = TestUtil.extractData(guestNameAndCrewIdQueryResultObject);
    }

    @Test
    public void seasonIdIsCorrect() {
        assertThat(getResult(seasonIdJson).get("id")).isEqualTo(new JsonPrimitive(3676));
    }

    @Test
    public void episodeAirdatesAreCorrect() {
        JsonElement datesJson = getResult(episodeNameJson).get("episodes");
        List<TvEpisodeType> dates = gson.fromJson(datesJson, new TypeToken<List<TvEpisodeType>>(){}.getType());

        assertThat(dates).hasSize(24);
        assertThat(dates.stream().map(TvEpisodeType::getName))
                .containsSequence("Dying Changes Everything", "Not Cancer", "Adverse Events", "Birthmarks", "Lucky Thirteen");
    }

    @Test
    public void crewJobsAreCorrect() {
        JsonElement episodes = getResult(crewJobJson).get("episodes");
        List<TvEpisodeType_TestClass> dates = gson.fromJson(episodes, new TypeToken<List<TvEpisodeType_TestClass>>(){}.getType());

        List<String> jobs = dates.stream()
                .flatMap(t -> t.getCrew().stream())
                .map(TvCrewType::getJob)
                .collect(Collectors.toList());

        assertThat(jobs).containsSequence("Writer", "Director", "Director", "Writer", "Writer");
    }

    @Test
    public void guestNameAndCrewIdAreCorrect() {
        JsonElement episodes = getResult(guestNameCrewIdJson).get("episodes");
        List<TvEpisodeType_TestClass> guestsCrew = gson.fromJson(episodes, new TypeToken<List<TvEpisodeType_TestClass>>(){}.getType());

        List<Integer> ids = guestsCrew.stream()
                .flatMap(t -> t.getCrew().stream())
                .map(TvCrewType::getId)
                .collect(Collectors.toList());

        assertThat(ids).containsSequence(1219508, 45645, 1215383, 1223963, 169061);

        List<String> names = guestsCrew.stream()
                .flatMap(t -> t.getGuestStars_Test().stream())
                .map(TvGuestStarType::getName)
                .collect(Collectors.toList());

        assertThat(names).containsSequence("Jamie Rose", "Christine Woods", "Michael Weston", "Tim Conlon", "Felicia Day");
    }

    private static JsonObject getResult(JsonObject jsonObject) {
        return jsonObject.get("tvSeasonSearch").getAsJsonObject();
    }

    private static List<FieldProducer> mockFields() {
        TvDao tvDao = mock(TvDao.class);

        when(tvDao.getTvSeason(1408, 5)).thenReturn(TestTypeInstances.getTvSeason());

        List<FieldProducer> fieldProducers = new ArrayList<>();
        fieldProducers.add(new TvSeasonSearchSchema(tvDao));

        return fieldProducers;
    }

    private class TvEpisodeType_TestClass extends TvEpisodeType {

        @SerializedName("guestStars")
        private List<TvGuestStarType> guestStarsTest;

        List<TvGuestStarType> getGuestStars_Test() {
            return guestStarsTest;
        }

        void setGuestStars_Test(List<TvGuestStarType> guestStars) {
            this.guestStarsTest = guestStars;
        }
    }
}
