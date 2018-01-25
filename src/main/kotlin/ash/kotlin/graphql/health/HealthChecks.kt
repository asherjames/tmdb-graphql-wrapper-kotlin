package ash.kotlin.graphql.health

import ash.kotlin.graphql.AppConfig
import com.codahale.metrics.health.HealthCheck
import com.mashape.unirest.http.Unirest
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class AppHealthCheck : HealthCheck()
{
  override fun check(): Result
  {
    return Result.healthy()
  }
}

class TmdbHealthCheck(private val config: AppConfig) : HealthCheck()
{
  private val log: Logger = LoggerFactory.getLogger(TmdbHealthCheck::class.java)

  override fun check(): Result
  {
    log.info("TmdbHealthCheck using config: $config")

    val response = Unirest
        .get(config.baseUrl)
        .queryString(mapOf("api_key" to config.apikey))
        .asString()

    return if (response.status == 200)
    {
      Result.healthy()
    } else
    {
      Result.unhealthy("Tmdb return status code ${response.status} with body ${response.body}")
    }
  }
}
