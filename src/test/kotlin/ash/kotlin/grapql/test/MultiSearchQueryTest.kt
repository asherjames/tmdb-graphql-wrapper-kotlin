package ash.kotlin.grapql.test

import ash.java.graphql.test.TestUtil
import ash.java.graphql.test.schemas.TestTypeInstances
import ash.kotlin.graphql.TmdbSchema
import ash.kotlin.graphql.data.SearchDao
import ash.kotlin.graphql.fields.FieldProducer
import ash.kotlin.graphql.fields.MultiSearchSchema
import ash.kotlin.graphql.types.movie.MovieType
import org.assertj.core.api.Assertions.*
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.google.gson.reflect.TypeToken
import org.junit.BeforeClass
import org.junit.Test

class MultiSearchQueryTest {

    @Test
    fun correctValueInPersonNameQuery() {
        assertThat(getPeople(personNameQueryJson)["name"]).isEqualTo(JsonPrimitive("Sigourney Weaver"))
    }

    @Test
    fun personNameQueryContainsOnlyPersonResults() {
        assertThat(getMovies(personNameQueryJson).entrySet().isEmpty()).isTrue()
        assertThat(getPeople(personNameQueryJson).entrySet().isEmpty()).isFalse()
        assertThat(getTvShows(personNameQueryJson).entrySet().isEmpty()).isTrue()
    }

    @Test
    fun correctValueInMovieTitleQuery() {
        assertThat(getMovies(movieTitleQueryJson)["title"]).isEqualTo(JsonPrimitive("Das Boot"))
    }

    @Test
    fun movieTitleQueryContainsOnlyMovieResults() {
        assertThat(getMovies(movieTitleQueryJson).entrySet().isEmpty()).isFalse()
        assertThat(getPeople(movieTitleQueryJson).entrySet().isEmpty()).isTrue()
        assertThat(getTvShows(movieTitleQueryJson).entrySet().isEmpty()).isTrue()
    }

    @Test
    fun correctValueInTvShowNameQuery() {
        assertThat(getTvShows(tvShowNameQueryJson)["name"]).isEqualTo(JsonPrimitive("House"))
    }

    @Test
    fun tvShowNameQueryContainsOnlyTvShowResults() {
        assertThat(getMovies(tvShowNameQueryJson).entrySet().isEmpty()).isTrue()
        assertThat(getPeople(tvShowNameQueryJson).entrySet().isEmpty()).isTrue()
        assertThat(getTvShows(tvShowNameQueryJson).entrySet().isEmpty()).isFalse()
    }

    @Test
    fun moviePersonQueryHasCorrectValues() {
        assertThat(getPeople(moviePersonQueryJson)["profilePath"]).isEqualTo(JsonPrimitive("/wlg55BTcp3kqfTb3zDtqOFyqhDR.jpg"))
        assertThat(getMovies(moviePersonQueryJson)["releaseDate"]).isEqualTo(JsonPrimitive("1981-09-16"))
    }

    @Test
    fun moviePersonQueryContainsOnlyMovieAndPersonResults() {
        assertThat(getMovies(moviePersonQueryJson).entrySet().isEmpty()).isFalse()
        assertThat(getPeople(moviePersonQueryJson).entrySet().isEmpty()).isFalse()
        assertThat(getTvShows(moviePersonQueryJson).entrySet().isEmpty()).isTrue()
    }

    @Test
    fun multiTypeQueryContainsCorrectPerson() {
        assertThat(getPeople(multiTypeQueryJson)["id"]).isEqualTo(JsonPrimitive(10205))
    }

    @Test
    fun multiTypeQueryContainsCorrectMovie() {
        val movie = getMovies(multiTypeQueryJson)
        val genreListType = object : TypeToken<List<Int>>(){}.type

        assertThat(movie.get("overview")).isEqualTo(JsonPrimitive("A German submarine hunts allied ships..."))

        val genreIds = gson.fromJson<List<Int>>(movie.get("genreIds"), genreListType)
        assertThat(genreIds).containsExactlyInAnyOrder(28, 18, 36, 10752, 12)

        assertThat(movie.get("originalLanguage")).isEqualTo(JsonPrimitive("de"))
    }

    @Test
    fun multiTypeQueryContainsCorrectTvShow() {
        val tvShow = getTvShows(multiTypeQueryJson)

        assertThat(tvShow.get("popularity")).isEqualTo(JsonPrimitive(14.202559))
        assertThat(tvShow.get("firstAirDate")).isEqualTo(JsonPrimitive("2004-11-16"))
        assertThat(tvShow.get("originalName")).isEqualTo(JsonPrimitive("House"))
    }

    @Test
    fun multiTypeQueryContainsOneOfEachType() {
        assertThat(getMovies(multiTypeQueryJson).entrySet().isEmpty()).isFalse()
        assertThat(getPeople(multiTypeQueryJson).entrySet().isEmpty()).isFalse()
        assertThat(getTvShows(multiTypeQueryJson).entrySet().isEmpty()).isFalse()
    }

    @Test
    fun nullQueryResultsInCorrectError() {
        assertThat(nullQueryJson).isNotNull
        val errorObject = nullQueryJson.get(0).asJsonObject
        assertThat(errorObject.get("validationErrorType").asString).isEqualTo("MissingFieldArgument")
        assertThat(errorObject.get("description").asString).isEqualTo("Missing field argument query")
    }

    private fun getMovies(input: JsonObject): JsonObject {
        return getResultElement(input, 0)
    }

    private fun getPeople(input: JsonObject): JsonObject {
        return getResultElement(input, 1)
    }

    private fun getTvShows(input: JsonObject): JsonObject {
        return getResultElement(input, 2)
    }

    private fun getResultElement(input: JsonObject, type: Int): JsonObject {
        return input.get("multiSearch").asJsonArray.get(type).asJsonObject
    }

    companion object {

        private val gson = Gson()

        private lateinit var personNameQueryJson: JsonObject
        private lateinit var movieTitleQueryJson: JsonObject
        private lateinit var tvShowNameQueryJson: JsonObject
        private lateinit var moviePersonQueryJson: JsonObject
        private lateinit var multiTypeQueryJson: JsonObject
        private lateinit var nullQueryJson: JsonArray

        @BeforeClass
        @JvmStatic
        fun setupResults() {
            val schema = TmdbSchema(mockFields())

            val personNameQueryResultObject = schema.executeQuery("{multiSearch(query: \"query input\"){" + "... on Person {name}}}")

            personNameQueryJson = TestUtil.extractData(personNameQueryResultObject)

            val movieTitleQueryResultObject = schema.executeQuery("{multiSearch(query: \"query input\"){" + "... on Movie{title}}}")

            movieTitleQueryJson = TestUtil.extractData(movieTitleQueryResultObject)

            val tvShowTitleQueryResultObject = schema.executeQuery("{multiSearch(query: \"query input\"){" + "... on TvShow {name}}}")

            tvShowNameQueryJson = TestUtil.extractData(tvShowTitleQueryResultObject)

            val moviePersonQueryResultObject = schema.executeQuery("{multiSearch(query: \"query input\"){"
                    + "... on Person {profilePath}"
                    + "... on Movie {releaseDate}}}")

            moviePersonQueryJson = TestUtil.extractData(moviePersonQueryResultObject)

            val multiTypeQueryResultObject = schema.executeQuery("{multiSearch(query: \"query input\"){"
                    + "... on Person {id}"
                    + "... on Movie {overview genreIds originalLanguage}"
                    + "... on TvShow{popularity firstAirDate originalName}}}")

            multiTypeQueryJson = TestUtil.extractData(multiTypeQueryResultObject)

            val nullQueryResultObject = schema.executeQuery("{multiSearch(page: 1){" + "... on Person {name}}}")

            nullQueryJson = TestUtil.extractError(nullQueryResultObject)
        }

        private fun mockFields(): List<FieldProducer> {
            class SearchDaoStub : SearchDao {
                override fun searchMoviesWithQuery(query: String): List<MovieType> {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun searchMoviesWithMultipleParameters(params: Map<String, Any>): List<MovieType> {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun searchMultiSearch(params: Map<String, Any>): List<Any> {
                    return listOf(TestTypeInstances.getMovie(),
                            TestTypeInstances.getPerson(),
                            TestTypeInstances.getTvShow())
                }
            }

            return listOf(MultiSearchSchema(SearchDaoStub()))
        }
    }
}