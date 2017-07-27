package ash.java.graphql.fields;

import ash.java.graphql.data.GenreDao;
import ash.java.graphql.types.genre.GenreType;
import graphql.schema.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static graphql.schema.GraphQLFieldDefinition.newFieldDefinition;

@Service
public class GenreSchema implements FieldProducer {

    private GenreDao genreDao;

    @Autowired
    public GenreSchema(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    private GraphQLFieldDefinition genreListFieldDefinition = newFieldDefinition()
            .type(new GraphQLList(new GenreType().getGraphQlType()))
            .name("genres")
            .dataFetcher(env -> genreDao.getAllMovieGenres())
            .build();

    public GraphQLFieldDefinition getFieldDefinition() {
        return genreListFieldDefinition;
    }
}
