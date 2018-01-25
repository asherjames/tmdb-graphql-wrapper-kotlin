package ash.kotlin.grapql.test

import ash.kotlin.graphql.TmdbSchema
import ash.kotlin.graphql.data.TmdbUtil
import ash.kotlin.graphql.fields.MovieKeywordFieldDefinition
import ash.kotlin.graphql.types.keyword.KeywordType
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import org.assertj.core.api.Assertions.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when`
import org.mockito.Mockito.any

@RunWith(MockitoJUnitRunner.Silent::class)
class KeywordQueryTest
{
  private val keywordArray = "keywordList"

  private val nameField = "name"

  private val idField = "id"

  private lateinit var resultObject: Any
  private lateinit var resultJson: JsonObject
  private lateinit var resultIdJson: JsonObject
  private lateinit var resultNameJson: JsonObject

  @Mock
  private lateinit var tmdbutil: TmdbUtil

  @InjectMocks
  private lateinit var fieldDefinition: MovieKeywordFieldDefinition

  @Before
  fun setupResults()
  {
    setupMocks()

    val schema = TmdbSchema(listOf(fieldDefinition))

    resultObject = schema.executeQuery("{keywordList(filmId: 123){id name}}")
    resultJson = extractData(resultObject)

    val resultIdObject = schema.executeQuery("{keywordList(filmId: 123){id}}")
    resultIdJson = extractData(resultIdObject)

    val resultNameObject = schema.executeQuery("{keywordList(filmId: 123){name}}")
    resultNameJson = extractData(resultNameObject)
  }

  private fun setupMocks()
  {
    `when`(tmdbutil.getMovieKeywords(any(Int::class.java))).thenReturn(listOf(
        KeywordType(603, "elves"),
        KeywordType(604, "dwarves"),
        KeywordType(611, "hobbit"),
        KeywordType(10364, "mission"))
    )
  }

  @Test
  fun correctQueryShouldNotReturnNull()
  {
    assertThat(resultObject).isNotNull()
  }

  @Test
  fun correctQueryShouldReturnJsonArrayOfCorrectLength()
  {
    assertThat(resultJson[keywordArray].asJsonArray.size()).isEqualTo(4)
  }

  @Test
  fun correctQueryShouldReturnListWithCorrectKeywords()
  {
    assertThat(resultJson[keywordArray].asJsonArray[0].asJsonObject[nameField])
        .isEqualTo(JsonPrimitive("elves"))

    assertThat(resultJson[keywordArray].asJsonArray[0].asJsonObject[idField])
        .isEqualTo(JsonPrimitive(603))
  }

  @Test
  fun correctIdQueryShouldReturnListWithJustIds()
  {
    assertThat(resultIdJson[keywordArray].asJsonArray[3].asJsonObject[idField])
        .isEqualTo(JsonPrimitive(10364))

    assertThat(resultIdJson[keywordArray].asJsonArray[0].asJsonObject[nameField])
        .isNull()

  }

  @Test
  fun correctNameQueryReturnsListWithJustNames()
  {
    assertThat(resultNameJson[keywordArray].asJsonArray[0].asJsonObject[idField])
        .isNull()

    assertThat(resultNameJson[keywordArray].asJsonArray[2].asJsonObject[nameField])
        .isEqualTo(JsonPrimitive("hobbit"))
  }
}