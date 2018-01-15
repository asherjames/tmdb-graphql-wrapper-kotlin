package ash.kotlin.graphql

import io.dropwizard.Application
import io.dropwizard.setup.Environment

class TmdbApplication : Application<AppConfig>()
{
    override fun run(configuration: AppConfig, environment: Environment)
    {
        environment.jersey().register(TmdbGqlResource::class.java)
    }
}

fun main(args: Array<String>)
{
    TmdbApplication().run(*args)
}
