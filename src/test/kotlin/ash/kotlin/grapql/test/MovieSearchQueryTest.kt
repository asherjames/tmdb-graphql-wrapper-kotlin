package ash.kotlin.grapql.test

import ash.kotlin.graphql.TmdbSchema
import ash.kotlin.graphql.data.TmdbUtil
import ash.kotlin.graphql.fields.MovieSearchFieldDefinition
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import org.assertj.core.api.Assertions.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyList
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class MovieSearchQueryTest
{
  private lateinit var posterPathResultObject: Any
  private lateinit var posterPathResultJson: JsonObject
  private lateinit var multipleFieldsResultJson: JsonObject
  private lateinit var nullQueryResultJson: JsonArray

  @Mock
  private lateinit var tmdbutil: TmdbUtil

  @InjectMocks
  private lateinit var fieldDefinition: MovieSearchFieldDefinition

  @Before
  fun setupResults()
  {
    setupMocks()

    val schema = TmdbSchema(listOf(fieldDefinition))

    posterPathResultObject = schema.executeQuery("{movieSearch(query: \"Das Boot\"){posterPath}}")
    posterPathResultJson = extractData(posterPathResultObject)

    val multipleFieldsResultObject = schema.executeQuery("{movieSearch(query: \"Das Boot\"){releaseDate title popularity voteCount}}")
    multipleFieldsResultJson = extractData(multipleFieldsResultObject)

    val nullQueryResultObject = schema.executeQuery("{movieSearch(year: 1981){releaseDate title popularity voteCount}}")
    nullQueryResultJson = extractError(nullQueryResultObject)
  }

  private fun setupMocks()
  {
    `when`(tmdbutil.searchMoviesWithMultipleParameters(anyList())).thenReturn(listOf(getMovie()))
  }

  @Test
  fun correctPosterpathQueryShouldNotReturnNull()
  {
    assertThat(posterPathResultObject).isNotNull()
  }

  @Test
  fun correctPosterpathQueryShouldReturnCorrectValue()
  {
    assertThat(posterPathResultJson["movieSearch"].asJsonArray[0].asJsonObject["posterPath"])
        .isEqualTo(JsonPrimitive("/kI1rptTkqDWj6SBRsYwguBvPViT.jpg"))
  }

  @Test
  fun correctMultipleFieldQueryReturnsAllFields()
  {
    val queryObject = multipleFieldsResultJson["movieSearch"].asJsonArray[0].asJsonObject

    assertThat(queryObject["releaseDate"]).isEqualTo(JsonPrimitive("1981-09-16"))
    assertThat(queryObject["title"]).isEqualTo(JsonPrimitive("Das Boot"))
    assertThat(queryObject["popularity"]).isEqualTo(JsonPrimitive(3.495501))
    assertThat(queryObject["voteCount"]).isEqualTo(JsonPrimitive(501))
  }

  @Test
  fun nullQueryReturnsError()
  {
    val errorObject = nullQueryResultJson[0].asJsonObject

    assertThat(errorObject["description"].asString).isEqualTo("Missing field argument query")
  }
}