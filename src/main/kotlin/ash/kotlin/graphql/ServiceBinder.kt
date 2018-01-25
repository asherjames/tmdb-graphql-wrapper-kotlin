package ash.kotlin.graphql

import ash.kotlin.graphql.data.TmdbUtilImpl
import org.glassfish.hk2.utilities.binding.AbstractBinder

class ServiceBinder(private val config: AppConfig) : AbstractBinder()
{
  override fun configure()
  {
    bind(config).to(AppConfig::class.java)
    bind(TmdbUtilImpl::class.java).to(TmdbUtilImpl::class.java)
  }
}