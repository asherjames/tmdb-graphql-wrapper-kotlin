package ash.kotlin.graphql.fields

import ash.kotlin.graphql.data.MovieDao
import ash.kotlin.graphql.types.keyword.KeywordType
import graphql.Scalars
import graphql.schema.GraphQLFieldDefinition
import graphql.schema.GraphQLList

class KeywordSchema constructor(private val dao: MovieDao) : FieldProducer
{
    override fun getFieldDefinition(): GraphQLFieldDefinition
    {
        return GraphQLFieldDefinition.newFieldDefinition()
                .type(GraphQLList(KeywordType().getGraphQlType()))
                .name("keywordList")
                .argument { arg -> arg.name("filmId").type(Scalars.GraphQLInt) }
                .dataFetcher { env -> dao.getKeywordsForMovie(env.getArgument("filmId")) }
                .build()
    }
}