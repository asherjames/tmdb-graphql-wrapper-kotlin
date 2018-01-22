package ash.kotlin.graphql.fields

import ash.kotlin.graphql.data.TmdbUtil
import ash.kotlin.graphql.types.keyword.KeywordType
import graphql.Scalars
import graphql.schema.GraphQLFieldDefinition
import graphql.schema.GraphQLList

class MovieKeywordFieldDefinition constructor(private val tmdbUtil: TmdbUtil) : FieldDefiner
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