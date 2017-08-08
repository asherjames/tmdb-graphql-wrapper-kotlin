package ash.kotlin.grapql.test

import ash.java.graphql.test.TestUtil
import ash.kotlin.graphql.TmdbSchema
import ash.kotlin.graphql.data.TvDao
import ash.kotlin.graphql.fields.FieldProducer
import ash.kotlin.graphql.fields.TvSeasonSearchSchema
import ash.kotlin.graphql.types.tvseason.TvSeasonType
import com.google.gson.JsonObject
import org.junit.BeforeClass

class TvSeasonSearchSchemaTest {

    companion object {
        private lateinit var seasonIdJson: JsonObject
        private lateinit var episodeNameJson: JsonObject
        private lateinit var crewJobJson: JsonObject
        private lateinit var guestNameCrewIdJson: JsonObject

        @BeforeClass
        @JvmStatic
        fun setupResults() {
            val schema = TmdbSchema(mockFields())

            val seasonIdQueryResultObject = schema.executeQuery("{tvSeasonSearch(tvId: 1408, seasonNum: 5){id}}")
            seasonIdJson = TestUtil.extractData(seasonIdQueryResultObject)

            val episodeNameQueryResultObject = schema.executeQuery("{tvSeasonSearch(tvId: 1408, seasonNum: 5){episodes{name}}}")
            episodeNameJson = TestUtil.extractData(episodeNameQueryResultObject)

            val crewJobQueryResultObject = schema.executeQuery("{tvSeasonSearch(tvId: 1408, seasonNum: 5){episodes{crew{job}}}}")
            crewJobJson = TestUtil.extractData(crewJobQueryResultObject)

            val guestNameAndCrewIdQueryResultObject = schema.executeQuery("{tvSeasonSearch(tvId: 1408, seasonNum: 5){episodes{crew{id} guestStars{name}}}}")
            guestNameCrewIdJson = TestUtil.extractData(guestNameAndCrewIdQueryResultObject)

        }

        private fun mockFields() : List<FieldProducer> {
            class TvDaoStub : TvDao {
                override fun getTvSeason(tvShowId: Int, seasonNumber: Int): TvSeasonType {
                    return ash.java.graphql.test.schemas.TestTypeInstances.getTvSeason()
                }
            }

            return listOf(TvSeasonSearchSchema(TvDaoStub()))
        }
    }
}
