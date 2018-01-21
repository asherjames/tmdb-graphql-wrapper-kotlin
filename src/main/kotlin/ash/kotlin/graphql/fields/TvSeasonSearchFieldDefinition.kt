package ash.kotlin.graphql.fields

import ash.kotlin.graphql.data.TvDao
import ash.kotlin.graphql.types.tvseason.TvSeasonType
import graphql.Scalars
import graphql.schema.GraphQLFieldDefinition
import graphql.schema.GraphQLNonNull

class TvSeasonSearchFieldDefinition constructor(private val dao: TvDao) : FieldDefiner
{
    override fun getFieldDefinition(): GraphQLFieldDefinition
    {
        return GraphQLFieldDefinition.newFieldDefinition()
                .type(TvSeasonType().getGraphQlType())
                .name("tvSeasonSearch")
                .argument { arg -> arg.name("tvId").type(GraphQLNonNull(Scalars.GraphQLInt)) }
                .argument { arg -> arg.name("seasonNum").type(GraphQLNonNull(Scalars.GraphQLInt)) }
                .dataFetcher { env -> dao.getTvSeason(env.getArgument("tvId"), env.getArgument("seasonNum")) }
                .build()
    }
}