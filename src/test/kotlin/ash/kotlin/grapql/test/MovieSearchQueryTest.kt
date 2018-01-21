package ash.kotlin.grapql.test

import ash.kotlin.graphql.TmdbSchema
import ash.kotlin.graphql.data.SearchDao
import ash.kotlin.graphql.fields.FieldDefiner
import ash.kotlin.graphql.fields.MovieSearchFieldDefinition
import ash.kotlin.graphql.types.movie.MovieType
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import org.assertj.core.api.Assertions.*
import org.junit.BeforeClass
import org.junit.Test
import java.util.ArrayList

class MovieSearchQueryTest
{

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

    companion object
    {

        private lateinit var posterPathResultObject: Any
        private lateinit var posterPathResultJson: JsonObject
        private lateinit var multipleFieldsResultJson: JsonObject
        private lateinit var nullQueryResultJson: JsonArray

        @BeforeClass
        @JvmStatic
        fun setupResults()
        {
            val schema = TmdbSchema(mockFields())

            posterPathResultObject = schema.executeQuery("{movieSearch(query: \"Das Boot\"){posterPath}}")
            posterPathResultJson = extractData(posterPathResultObject)

            val multipleFieldsResultObject = schema.executeQuery("{movieSearch(query: \"Das Boot\"){releaseDate title popularity voteCount}}")
            multipleFieldsResultJson = extractData(multipleFieldsResultObject)

            val nullQueryResultObject = schema.executeQuery("{movieSearch(year: 1981){releaseDate title popularity voteCount}}")
            nullQueryResultJson = extractError(nullQueryResultObject)
        }

        private fun mockFields(): List<FieldDefiner>
        {
            class SearchDaoStub : SearchDao
            {
                override fun searchMoviesWithQuery(query: String): List<MovieType>
                {
                    return ArrayList()
                }

                override fun searchMoviesWithMultipleParameters(params: Map<String, Any>): List<MovieType>
                {
                    return listOf(getMovie())
                }

                override fun searchMultiSearch(params: Map<String, Any>): List<Any>
                {
                    return ArrayList()
                }
            }

            return listOf(MovieSearchFieldDefinition(SearchDaoStub()))
        }
    }
}