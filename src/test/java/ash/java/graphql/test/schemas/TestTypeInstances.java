package ash.java.graphql.test.schemas;

import ash.java.graphql.test.TestUtil;
import ash.java.graphql.types.movie.MovieType;
import ash.java.graphql.types.multisearch.PersonType;
import ash.java.graphql.types.multisearch.TvShowType;
import ash.java.graphql.types.tvseason.TvSeasonType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class TestTypeInstances {

    private static Gson gson = new Gson();

    public static MovieType getMovie() {
        MovieType movie = new MovieType();
        movie.setPosterPath("/kI1rptTkqDWj6SBRsYwguBvPViT.jpg");
        movie.setAdult(false);
        movie.setOverview("A German submarine hunts allied ships...");
        movie.setReleaseDate("1981-09-16");
        movie.setGenreIds(Arrays.asList(28, 18, 36, 10752, 12));
        movie.setId(387);
        movie.setOriginalTitle("Das Boot");
        movie.setOriginalLanguage("de");
        movie.setTitle("Das Boot");
        movie.setBackdropPath("/mc0PbKrrFRCUEpI09reR3ihHrIo.jpg");
        movie.setPopularity(3.495501);
        movie.setVoteCount(501);
        movie.setVideo(false);
        movie.setVoteAverage(7.9);

        return movie;
    }

    public static PersonType getPerson() {
        PersonType person = new PersonType();
        person.setProfilePath("/wlg55BTcp3kqfTb3zDtqOFyqhDR.jpg");
        person.setAdult(false);
        person.setId(10205);
        person.setMediaType("person");
        person.setName("Sigourney Weaver");
        person.setPopularity(8.978738);

        return person;
    }

    public static TvShowType getTvShow() {
        TvShowType tvShowType = new TvShowType();
        tvShowType.setPosterPath("/lxSzRZ49NXwsiyHuvMsd19QxduC.jpg");
        tvShowType.setPopularity(14.202559);
        tvShowType.setId(1408);
        tvShowType.setOverview("Dr. Gregory House, a drug-addicted, unconventional, misanthropic medical genius...");
        tvShowType.setBackdropPath("/6r5o8yvLx7nESRBC1iMuYBCk9Cj.jpg");
        tvShowType.setVoteAverage(7.89);
        tvShowType.setMediaType("tv");
        tvShowType.setFirstAirDate("2004-11-16");
        tvShowType.setOriginCountry(Arrays.asList("US"));
        tvShowType.setGenreIds(Arrays.asList(18, 35, 9648));
        tvShowType.setOriginalLanguage("en");
        tvShowType.setVoteCount(418);
        tvShowType.setName("House");
        tvShowType.setOriginalName("House");

        return tvShowType;
    }

    public static TvSeasonType getTvSeason() {
        Type tvSeasonType = new TypeToken<TvSeasonType>(){}.getType();
        String jsonString = TestUtil.loadTestResource("house_season_5.json");

        return gson.fromJson(jsonString, tvSeasonType);
    }
}
