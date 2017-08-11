package ash.kotlin.grapql.test

import ash.kotlin.graphql.TmdbSchema
import ash.kotlin.graphql.data.GenreDao
import ash.kotlin.graphql.fields.FieldProducer
import ash.kotlin.graphql.fields.GenreSchema
import ash.kotlin.graphql.types.genre.GenreType
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import org.assertj.core.api.Assertions.*
import org.junit.BeforeClass
import org.junit.Test

class GenreQueryTest {

    @Test
    fun correctQueryShouldNotReturnNull() {
        assertThat(resultObjectIdName).isNotNull()
    }

    @Test
    fun correctQueryShouldReturnCorrectNumberOfGenres() {
        assertThat(resultJsonIdName["genres"].asJsonArray.size()).isEqualTo(3)
    }

    @Test
    fun correctQueryShouldReturnListWithCorrectValues() {
        assertThat(resultJsonIdName["genres"].asJsonArray[0].asJsonObject["id"])
                .isEqualTo(JsonPrimitive(28))

        assertThat(resultJsonIdName["genres"].asJsonArray[0].asJsonObject["name"])
                .isEqualTo(JsonPrimitive("Action"))
    }

    @Test
    fun correctIdQueryReturnsListWithJustIds() {
        assertThat(resultJsonId["genres"].asJsonArray[0].asJsonObject["id"])
                .isEqualTo(JsonPrimitive(28))

        assertThat(resultJsonId["genres"].asJsonArray[0].asJsonObject["name"])
                .isNull()
    }

    @Test
    fun correctNameQueryReturnsListWithJustNames() {
        assertThat(resultJsonName["genres"].asJsonArray[0].asJsonObject["id"])
                .isNull()

        assertThat(resultJsonName["genres"].asJsonArray[0].asJsonObject["name"])
                .isEqualTo(JsonPrimitive("Action"))
    }

    companion object {
        private lateinit var resultObjectIdName: Any
        private lateinit var resultJsonIdName: JsonObject
        private lateinit var resultJsonId: JsonObject
        private lateinit var resultJsonName: JsonObject

         @BeforeClass
         @JvmStatic
         fun setupResults() {
            val schema = TmdbSchema(mockFields())

            resultObjectIdName = schema.executeQuery("{genres{id name}}")
            resultJsonIdName = extractData(resultObjectIdName)

            val resultObjectId = schema.executeQuery("{genres{id}}")
            resultJsonId = extractData(resultObjectId)

            val resultObjectName = schema.executeQuery("{genres{name}}")
            resultJsonName = extractData(resultObjectName)
        }

        fun mockFields(): List<FieldProducer> {
            class GenreDaoStub : GenreDao {
                override fun getAllMovieGenres(): List<GenreType> {
                    return listOf(
                            GenreType(28, "Action"),
                            GenreType(35, "Comedy"),
                            GenreType(12, "Fantasy"))
                }
            }

            return listOf(GenreSchema(GenreDaoStub()))
        }
    }
}