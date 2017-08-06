package ash.kotlin.grapql.test

import ash.java.graphql.test.TestUtil
import ash.kotlin.graphql.TmdbSchema
import ash.kotlin.graphql.data.MovieDao
import ash.kotlin.graphql.fields.FieldProducer
import ash.kotlin.graphql.fields.KeywordSchema
import ash.kotlin.graphql.types.keyword.KeywordType
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import org.assertj.core.api.Assertions.*
import org.junit.BeforeClass
import org.junit.Test

class KeywordQueryTest {

    @Test
    fun correctQueryShouldNotReturnNull() {
        assertThat(resultObject).isNotNull()
    }

    @Test
    fun correctQueryShouldReturnJsonArrayOfCorrectLength() {
        assertThat(resultJson["keywordList"].asJsonArray.size()).isEqualTo(4)
    }

    @Test
    fun correctQueryShouldReturnListWithCorrectKeywords() {
        assertThat(resultJson["keywordList"].asJsonArray[0].asJsonObject["name"])
                .isEqualTo(JsonPrimitive("elves"))

        assertThat(resultJson["keywordList"].asJsonArray[0].asJsonObject["id"])
                .isEqualTo(JsonPrimitive(603))
    }

    @Test
    fun correctIdQueryShouldReturnListWithJustIds() {
        assertThat(resultIdJson["keywordList"].asJsonArray[3].asJsonObject["id"])
                .isEqualTo(JsonPrimitive(10364))

        assertThat(resultIdJson["keywordList"].asJsonArray[0].asJsonObject["name"])
                .isNull()

    }

    @Test
    fun correctNameQueryReturnsListWithJustNames() {
        assertThat(resultNameJson["keywordList"].asJsonArray[0].asJsonObject["id"])
                .isNull()

        assertThat(resultNameJson["keywordList"].asJsonArray[2].asJsonObject["name"])
                .isEqualTo(JsonPrimitive("hobbit"))
    }

    companion object {

        private lateinit var resultObject: Any
        private lateinit var resultJson: JsonObject
        private lateinit var resultIdJson: JsonObject
        private lateinit var resultNameJson: JsonObject

        @BeforeClass
        @JvmStatic
        fun setupResults() {
            val schema = TmdbSchema(mockFields())

            resultObject = schema.executeQuery("{keywordList(filmId: 123){id name}}")
            resultJson = TestUtil.extractData(resultObject)

            val resultIdObject = schema.executeQuery("{keywordList(filmId: 123){id}}")
            resultIdJson = TestUtil.extractData(resultIdObject)

            val resultNameObject = schema.executeQuery("{keywordList(filmId: 123){name}}")
            resultNameJson = TestUtil.extractData(resultNameObject)
        }

        private fun mockFields(): List<FieldProducer> {
            class MovieDaoStub : MovieDao {
                override fun getKeywordsForMovie(movieId: Int): List<KeywordType> {
                    return listOf(
                            KeywordType(603, "elves"),
                            KeywordType(604, "dwarves"),
                            KeywordType(611, "hobbit"),
                            KeywordType(10364, "mission")
                    )
                }
            }

            return listOf(KeywordSchema(MovieDaoStub()))
        }
    }
}