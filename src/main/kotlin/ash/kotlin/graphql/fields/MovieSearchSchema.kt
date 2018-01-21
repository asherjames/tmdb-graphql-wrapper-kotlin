package ash.kotlin.graphql.fields

import ash.kotlin.graphql.data.SearchDao
import ash.kotlin.graphql.types.movie.MovieType
import graphql.Scalars.*
import graphql.schema.*

class MovieSearchSchema constructor(private val dao: SearchDao) : FieldProducer
{
    override fun getFieldDefinition(): GraphQLFieldDefinition
    {
        return GraphQLFieldDefinition.newFieldDefinition()
                .type(GraphQLList(MovieType().getGraphQlType()))
                .name("movieSearch")
                .argument { arg -> arg.name("query").type(GraphQLNonNull(GraphQLString)) }
                .argument { arg -> arg.name("language").type(GraphQLString) }
                .argument { arg -> arg.name("page").type(GraphQLInt) }
                .argument { arg -> arg.name("includeAdult").type(GraphQLBoolean) }
                .argument { arg -> arg.name("region").type(GraphQLString) }
                .argument { arg -> arg.name("year").type(GraphQLInt) }
                .argument { arg -> arg.name("primaryReleaseYear").type(GraphQLInt) }
                .dataFetcher { env -> dao.searchMoviesWithMultipleParameters(env.arguments) }
                .build()
    }
}