package ash.kotlin.graphql.fields

import ash.kotlin.graphql.data.TmdbUtil
import ash.kotlin.graphql.types.genre.GenreType
import graphql.schema.*
import javax.inject.Inject

class GenreFieldDefinition @Inject constructor(private val tmdbUtil: TmdbUtil) : FieldDefiner
{
  override fun getFieldDefinition(): GraphQLFieldDefinition
  {
    return GraphQLFieldDefinition.newFieldDefinition()
        .type(GraphQLList(GenreType().getGraphQlType()))
        .name("genres")
        .dataFetcher { _ -> tmdbUtil.getGenreList() }
        .build()
  }
}