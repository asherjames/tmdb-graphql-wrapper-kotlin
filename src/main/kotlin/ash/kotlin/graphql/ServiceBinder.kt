package ash.kotlin.graphql

import ash.kotlin.graphql.data.TmdbUtil
import org.glassfish.hk2.utilities.binding.AbstractBinder

class ServiceBinder(private val config: AppConfig) : AbstractBinder()
{
    override fun configure()
    {
        bind(config).to(AppConfig::class.java)
        bind(TmdbUtil::class.java).to(TmdbUtil::class.java)
    }
}