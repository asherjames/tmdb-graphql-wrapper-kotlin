package ash.kotlin.graphql

import ash.kotlin.graphql.data.TmdbUtil
import ash.kotlin.graphql.data.TmdbUtilImpl
import ash.kotlin.graphql.fields.FieldDefiner
import ash.kotlin.graphql.fields.GenreFieldDefinition
import ash.kotlin.graphql.fields.MovieKeywordFieldDefinition
import ash.kotlin.graphql.fields.MovieSearchFieldDefinition
import ash.kotlin.graphql.fields.MultiSearchFieldDefinition
import ash.kotlin.graphql.fields.TvSeasonSearchFieldDefinition
import org.glassfish.hk2.utilities.binding.AbstractBinder

class ServiceBinder(private val config: AppConfig) : AbstractBinder()
{
  override fun configure()
  {
    bind(config).to(AppConfig::class.java)
    bind(TmdbUtilImpl::class.java).to(TmdbUtil::class.java)
    bind(TmdbSchema::class.java).to(TmdbSchema::class.java)

    bind(GenreFieldDefinition::class.java).to(FieldDefiner::class.java)
    bind(MovieKeywordFieldDefinition::class.java).to(FieldDefiner::class.java)
    bind(MovieSearchFieldDefinition::class.java).to(FieldDefiner::class.java)
    bind(MultiSearchFieldDefinition::class.java).to(FieldDefiner::class.java)
    bind(TvSeasonSearchFieldDefinition::class.java).to(FieldDefiner::class.java)
  }
}