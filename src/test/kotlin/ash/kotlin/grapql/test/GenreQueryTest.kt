package ash.kotlin.grapql.test

import ash.kotlin.graphql.TmdbSchema
import ash.kotlin.graphql.data.GenreDao
import ash.kotlin.graphql.fields.FieldDefiner
import ash.kotlin.graphql.fields.GenreFieldDefinition
import ash.kotlin.graphql.types.genre.GenreType
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import org.assertj.core.api.Assertions.*
import org.junit.BeforeClass
import org.junit.Test

class GenreQueryTest
{

    private val genreArray = "genres"

    private val idField = "id"

    private val nameField = "name"

    @Test
    fun correctQueryShouldNotReturnNull()
    {
        assertThat(resultObjectIdName).isNotNull()
    }

    @Test
    fun correctQueryShouldReturnCorrectNumberOfGenres()
    {
        assertThat(resultJsonIdName[genreArray].asJsonArray.size()).isEqualTo(3)
    }

    @Test
    fun correctQueryShouldReturnListWithCorrectValues()
    {
        assertThat(resultJsonIdName[genreArray].asJsonArray[0].asJsonObject[idField])
                .isEqualTo(JsonPrimitive(28))

        assertThat(resultJsonIdName[genreArray].asJsonArray[0].asJsonObject[nameField])
                .isEqualTo(JsonPrimitive("Action"))
    }

    @Test
    fun correctIdQueryReturnsListWithJustIds()
    {
        assertThat(resultJsonId[genreArray].asJsonArray[0].asJsonObject[idField])
                .isEqualTo(JsonPrimitive(28))

        assertThat(resultJsonId[genreArray].asJsonArray[0].asJsonObject[nameField])
                .isNull()
    }

    @Test
    fun correctNameQueryReturnsListWithJustNames()
    {
        assertThat(resultJsonName[genreArray].asJsonArray[0].asJsonObject[idField])
                .isNull()

        assertThat(resultJsonName[genreArray].asJsonArray[0].asJsonObject[nameField])
                .isEqualTo(JsonPrimitive("Action"))
    }

    companion object
    {
        private lateinit var resultObjectIdName: Any
        private lateinit var resultJsonIdName: JsonObject
        private lateinit var resultJsonId: JsonObject
        private lateinit var resultJsonName: JsonObject

        @BeforeClass
        @JvmStatic
        fun setupResults()
        {
            val schema = TmdbSchema(mockFields())

            resultObjectIdName = schema.executeQuery("{genres{id name}}")
            resultJsonIdName = extractData(resultObjectIdName)

            val resultObjectId = schema.executeQuery("{genres{id}}")
            resultJsonId = extractData(resultObjectId)

            val resultObjectName = schema.executeQuery("{genres{name}}")
            resultJsonName = extractData(resultObjectName)
        }

        fun mockFields(): List<FieldDefiner>
        {
            class GenreDaoStub : GenreDao
            {
                override fun getAllMovieGenres(): List<GenreType>
                {
                    return listOf(
                            GenreType(28, "Action"),
                            GenreType(35, "Comedy"),
                            GenreType(12, "Fantasy"))
                }
            }

            return listOf(GenreFieldDefinition(GenreDaoStub()))
        }
    }
}