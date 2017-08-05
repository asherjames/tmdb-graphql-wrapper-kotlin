package ash.kotlin.graphql

import ash.java.graphql.TmdbGqlException
import org.slf4j.*
import java.io.IOException
import java.io.InputStream
import java.util.*

object ApiKey {
    private val log: Logger = LoggerFactory.getLogger(ApiKey.javaClass)
    var apiKey: String
        private set

    init {
        try {
            log.info("Attempting to load API key from properties file...")
            val propertiesFile: InputStream = this.javaClass.getResourceAsStream("/apiKey.properties") ?:
                    throw TmdbGqlException("Could not find properties file!")
            val props: Properties = Properties()
            props.load(propertiesFile)
            apiKey = props.getProperty("apikey")
        }
        catch (e: IOException)
        {
            log.info("Attempting to load API key from environment variables...", e)
            apiKey = System.getenv("TMDB_API_KEY") ?:
                    throw TmdbGqlException("Could not load API key from environment variable!")
        }
    }
}