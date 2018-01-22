package ash.kotlin.graphql.fields

import ash.kotlin.graphql.data.TmdbUtilImpl
import ash.kotlin.graphql.types.genre.GenreType
import graphql.schema.*
import javax.inject.Inject

class GenreFieldDefinition(@Inject private val tmdbUtil: TmdbUtilImpl) : FieldDefiner
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