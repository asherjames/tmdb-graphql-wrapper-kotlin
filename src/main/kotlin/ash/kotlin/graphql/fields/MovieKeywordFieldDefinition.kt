package ash.kotlin.graphql.fields

import ash.kotlin.graphql.data.TmdbUtilImpl
import ash.kotlin.graphql.types.keyword.KeywordType
import graphql.Scalars
import graphql.schema.GraphQLFieldDefinition
import graphql.schema.GraphQLList
import javax.inject.Inject

class MovieKeywordFieldDefinition(@Inject private val tmdbUtil: TmdbUtilImpl) : FieldDefiner
{
    override fun getFieldDefinition(): GraphQLFieldDefinition
    {
        return GraphQLFieldDefinition.newFieldDefinition()
                .type(GraphQLList(KeywordType().getGraphQlType()))
                .name("keywordList")
                .argument { arg -> arg.name("filmId").type(Scalars.GraphQLInt) }
                .dataFetcher { env -> tmdbUtil.searchMoviesWithQuery(env.getArgument("filmId")) }
                .build()
    }
}