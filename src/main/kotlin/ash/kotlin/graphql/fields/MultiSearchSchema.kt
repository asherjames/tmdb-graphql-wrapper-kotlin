package ash.kotlin.graphql.fields

import ash.kotlin.graphql.data.TmdbUtilImpl
import ash.kotlin.graphql.types.movie.MovieType
import ash.kotlin.graphql.types.multisearch.PersonType
import ash.kotlin.graphql.types.multisearch.TvShowType
import graphql.Scalars.*
import graphql.schema.GraphQLFieldDefinition
import graphql.schema.GraphQLList
import graphql.schema.GraphQLNonNull
import graphql.schema.GraphQLUnionType
import javax.inject.Inject

class MultiSearchSchema(@Inject private val tmdbUtil: TmdbUtilImpl) : FieldDefiner
{
    override fun getFieldDefinition(): GraphQLFieldDefinition
    {
        return createMultiSearchField()
    }
    
    private fun createMultiSearchField(): GraphQLFieldDefinition
    {
        return GraphQLFieldDefinition.newFieldDefinition()
                .name("multiSearch")
                .type(GraphQLList(GraphQLUnionType.newUnionType()
                        .name("MultiSearchType")
                        .possibleType(PersonType().getGraphQlType())
                        .possibleType(MovieType().getGraphQlType())
                        .possibleType(TvShowType().getGraphQlType())
                        .typeResolver {
                            when (it)
                            {
                                is PersonType -> PersonType().getGraphQlType()
                                is MovieType -> MovieType().getGraphQlType()
                                is TvShowType -> TvShowType().getGraphQlType()
                                else -> null
                            }
                        }
                        .build()
                ))
                .argument { arg -> arg.name("query").type(GraphQLNonNull(GraphQLString)) }
                .argument { arg -> arg.name("language").type(GraphQLString) }
                .argument { arg -> arg.name("page").type(GraphQLInt) }
                .argument { arg -> arg.name("include_adult").type(GraphQLBoolean) }
                .argument { arg -> arg.name("region").type(GraphQLString) }
                .dataFetcher { env -> tmdbUtil.searchMulti(env.arguments.toList()) }
                .build()
    }
}