package ash.java.graphql.fields;

import ash.java.graphql.data.SearchDao;
import ash.java.graphql.types.movie.MovieType;
import ash.java.graphql.types.multisearch.PersonType;
import ash.java.graphql.types.multisearch.TvShowType;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLList;
import graphql.schema.GraphQLNonNull;
import graphql.schema.GraphQLUnionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLUnionType.newUnionType;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;

@Service
public class MultiSearchSchema implements FieldProducer {

    private SearchDao searchDao;

    @Autowired
    public MultiSearchSchema(SearchDao searchDao) {
        this.searchDao = searchDao;
    }

    private GraphQLUnionType multiSearchType = newUnionType()
            .name("MultiSearchType")
            .possibleType(new PersonType().getGraphQlType())
            .possibleType(new MovieType().getGraphQlType())
            .possibleType(new TvShowType().getGraphQlType())
            .typeResolver(object -> {
                if (object instanceof PersonType) {
                    return new PersonType().getGraphQlType();
                }
                if (object instanceof MovieType) {
                    return new MovieType().getGraphQlType();
                }
                if (object instanceof TvShowType) {
                    return new TvShowType().getGraphQlType();
                }
                return null;
            })
            .build();

    private GraphQLFieldDefinition multiSearchField = newFieldDefinition()
            .name("multiSearch")
            .type(new GraphQLList(multiSearchType))
            .argument(arg -> arg.name("query").type(new GraphQLNonNull(GraphQLString)))
            .argument(arg -> arg.name("language").type(GraphQLString))
            .argument(arg -> arg.name("page").type(GraphQLInt))
            .argument(arg -> arg.name("include_adult").type(GraphQLBoolean))
            .argument(arg -> arg.name("region").type(GraphQLString))
            .dataFetcher(env -> searchDao.searchMultiSearch(env.getArguments()))
            .build();

    @Override
    public GraphQLFieldDefinition getFieldDefinition() {
        return multiSearchField;
    }
}
