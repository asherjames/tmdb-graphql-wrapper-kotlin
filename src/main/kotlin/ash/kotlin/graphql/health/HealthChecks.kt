package ash.kotlin.graphql.health

import ash.kotlin.graphql.AppConfig
import com.codahale.metrics.health.HealthCheck
import com.mashape.unirest.http.Unirest
import javax.inject.Inject

class AppHealthCheck() : HealthCheck()
{
    override fun check(): Result
    {
        return Result.healthy()
    }
}

class TmdbHealthCheck(private val config: AppConfig) : HealthCheck()
{
    override fun check(): Result
    {
        val response = Unirest
                .get(config.baseUrl)
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
