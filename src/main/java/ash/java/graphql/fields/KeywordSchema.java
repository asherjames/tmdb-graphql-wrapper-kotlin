package ash.java.graphql.fields;

import ash.java.graphql.data.MovieDao;
import ash.java.graphql.types.keyword.KeywordType;
import graphql.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static graphql.Scalars.*;
import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;

@Service
public class KeywordSchema implements FieldProducer {

    private MovieDao movieDao;

    @Autowired
    public KeywordSchema(MovieDao movieDao) {
        this.movieDao = movieDao;
    }

    private GraphQLFieldDefinition keywordResultFieldType = newFieldDefinition()
            .type(new GraphQLList(new KeywordType().getGraphQlType()))
            .name("keywordList")
            .argument(arg -> arg.name("filmId")
                    .type(GraphQLInt))
            .dataFetcher(env -> movieDao.getKeywordsForMovie(env.getArgument("filmId")))
            .build();

    public GraphQLFieldDefinition getFieldDefinition() {
        return keywordResultFieldType;
    }
}
