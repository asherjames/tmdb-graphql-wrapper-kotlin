package ash.java.graphql.fields;

import ash.java.graphql.data.SearchDao;
import ash.java.graphql.types.movie.MovieType;
import graphql.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;

@Service
public class MovieSearchSchema implements FieldProducer {

    private SearchDao searchDao;

    @Autowired
    public MovieSearchSchema(SearchDao searchDao) {
        this.searchDao = searchDao;
    }

    private GraphQLFieldDefinition movieSearchFieldDefinition = newFieldDefinition()
            .type(new GraphQLList(new MovieType().getGraphQlType()))
            .name("movieSearch")
            .argument(arg -> arg.name("query").type(new GraphQLNonNull(GraphQLString)))
            .argument(arg -> arg.name("language").type(GraphQLString))
            .argument(arg -> arg.name("page").type(GraphQLInt))
            .argument(arg -> arg.name("includeAdult").type(GraphQLBoolean))
            .argument(arg -> arg.name("region").type(GraphQLString))
            .argument(arg -> arg.name("year").type(GraphQLInt))
            .argument(arg -> arg.name("primaryReleaseYear").type(GraphQLInt))
            .dataFetcher(env -> searchDao.searchMoviesWithMultipleParameters(env.getArguments()))
            .build();

    public GraphQLFieldDefinition getFieldDefinition() {
        return movieSearchFieldDefinition;
    }
}
