package ash.java.graphql.fields;


import ash.java.graphql.data.TvDao;
import ash.java.graphql.types.tvseason.TvSeasonType;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLNonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;

@Service
public class TvSeasonSearchSchema implements FieldProducer {

    private TvDao tvDao;

    @Autowired
    public TvSeasonSearchSchema(TvDao tvDao) {
        this.tvDao = tvDao;
    }

    private GraphQLFieldDefinition tvSeasonSearchFieldDefinition = newFieldDefinition()
            .type(new TvSeasonType().getGraphQlType())
            .name("tvSeasonSearch")
            .argument(arg -> arg.name("tvId").type(new GraphQLNonNull(GraphQLInt)))
            .argument(arg -> arg.name("seasonNum").type(new GraphQLNonNull(GraphQLInt)))
            .dataFetcher(env -> tvDao.getTvSeason(env.getArgument("tvId"), env.getArgument("seasonNum")))
            .build();

    @Override
    public GraphQLFieldDefinition getFieldDefinition() {
        return tvSeasonSearchFieldDefinition;
    }
}
