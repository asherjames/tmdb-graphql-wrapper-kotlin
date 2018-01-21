package ash.kotlin.graphql

import ash.kotlin.graphql.health.AppHealthCheck
import ash.kotlin.graphql.health.TmdbHealthCheck
import ash.kotlin.graphql.resource.TmdbGqlResource
import io.dropwizard.Application
import io.dropwizard.setup.Environment

class TmdbApplication : Application<AppConfig>()
{
    override fun run(configuration: AppConfig, environment: Environment)
    {
        // Binding
        environment.jersey().register(ServiceBinder(configuration))

        // Resource
        environment.jersey().register(TmdbGqlResource::class.java)

        // Healthchecks
        environment.healthChecks().register("App", AppHealthCheck())
        environment.healthChecks().register("Tmdb", TmdbHealthCheck(configuration))
    }
}

fun main(args: Array<String>)
{
    TmdbApplication().run(*args)
}
