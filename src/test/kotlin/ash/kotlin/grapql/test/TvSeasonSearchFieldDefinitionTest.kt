package ash.kotlin.grapql.test

import ash.kotlin.graphql.TmdbSchema
import ash.kotlin.graphql.data.TmdbUtil
import ash.kotlin.graphql.data.TvDao
import ash.kotlin.graphql.fields.FieldDefiner
import ash.kotlin.graphql.fields.MovieKeywordFieldDefinition
import ash.kotlin.graphql.fields.TvSeasonSearchFieldDefinition
import ash.kotlin.graphql.types.tvseason.TvEpisodeType
import ash.kotlin.graphql.types.tvseason.TvGuestStarType
import ash.kotlin.graphql.types.tvseason.TvSeasonType
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import org.assertj.core.api.Assertions.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class TvSeasonSearchFieldDefinitionTest
{

  private val gson: Gson = Gson()

  private lateinit var seasonIdJson: JsonObject
  private lateinit var episodeNameJson: JsonObject
  private lateinit var crewJobJson: JsonObject
  private lateinit var guestNameCrewIdJson: JsonObject

  @Mock
  private lateinit var tmdbutil: TmdbUtil

  @InjectMocks
  private lateinit var fieldDefinition: TvSeasonSearchFieldDefinition

  @Before
  fun setupResults()
  {
    setupMocks()

    val schema = TmdbSchema(listOf(fieldDefinition))

    val seasonIdQueryResultObject = schema.executeQuery("{tvSeasonSearch(tvId: 1408, seasonNum: 5){id}}")
    seasonIdJson = extractData(seasonIdQueryResultObject)

    val episodeNameQueryResultObject = schema.executeQuery("{tvSeasonSearch(tvId: 1408, seasonNum: 5){episodes{name}}}")
    episodeNameJson = extractData(episodeNameQueryResultObject)

    val crewJobQueryResultObject = schema.executeQuery("{tvSeasonSearch(tvId: 1408, seasonNum: 5){episodes{crew{job}}}}")
    crewJobJson = extractData(crewJobQueryResultObject)

    val guestNameAndCrewIdQueryResultObject = schema.executeQuery("{tvSeasonSearch(tvId: 1408, seasonNum: 5){episodes{crew{id} guestStars{name}}}}")
    guestNameCrewIdJson = extractData(guestNameAndCrewIdQueryResultObject)

  }

  private fun setupMocks()
  {
    `when`(tmdbutil.getTvSeason(any(Int::class.java), any(Int::class.java))).thenReturn(getTvSeason())
  }

  @Test
  fun seasonIdIsCorrect()
  {
    assertThat(getResult(seasonIdJson)["id"]).isEqualTo(JsonPrimitive(3676))
  }

  @Test
  fun episodeAirdatesAreCorrect()
  {
    val datesJson = getResult(episodeNameJson)["episodes"]
    val dates: List<TvEpisodeType> = gson.fromJson(datesJson, object : TypeToken<List<TvEpisodeType>>()
    {}.type)

    assertThat(dates).hasSize(24)

    assertThat<String>(dates.map { it.name })
        .containsSequence("Dying Changes Everything", "Not Cancer", "Adverse Events", "Birthmarks", "Lucky Thirteen")
  }

  @Test
  fun crewJobsAreCorrect()
  {
    val episodes = getResult(crewJobJson)["episodes"]
    val dates = gson.fromJson<List<TvEpisodeType_TestClass>>(episodes,
        object : TypeToken<List<TvEpisodeType_TestClass>>()
        {}.type)

    val jobs = dates
        .flatMap { it.crew }
        .map { it.job }
        .toList()

    assertThat(jobs).containsSequence("Writer", "Director", "Director", "Writer", "Writer")
  }

  @Test
  fun guestNameAndCrewIdAreCorrect()
  {
    val episodes = getResult(guestNameCrewIdJson)["episodes"]
    val guestsCrew = gson.fromJson<List<TvEpisodeType_TestClass>>(episodes,
        object : TypeToken<List<TvEpisodeType_TestClass>>()
        {}.type)

    val ids = guestsCrew
        .flatMap { it.crew }
        .map { it.id }
        .toList()

    assertThat(ids).containsSequence(1219508, 45645, 1215383, 1223963, 169061)

    val names = guestsCrew
        .flatMap { it.guestStars_Test }
        .map { it.name }

    assertThat(names).containsSequence("Jamie Rose", "Christine Woods", "Michael Weston", "Tim Conlon", "Felicia Day")
  }

  fun getResult(jsonObject: JsonObject): JsonObject
  {
    return jsonObject["tvSeasonSearch"].asJsonObject
  }

  private class TvEpisodeType_TestClass(@SerializedName("guestStars") var guestStars_Test: List<TvGuestStarType>) : TvEpisodeType()
}
