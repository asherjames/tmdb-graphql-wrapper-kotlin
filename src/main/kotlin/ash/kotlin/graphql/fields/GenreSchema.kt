package ash.kotlin.graphql.fields

import ash.java.graphql.data.GenreDao
import ash.kotlin.graphql.types.genre.GenreType
import graphql.schema.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GenreSchema @Autowired constructor(val dao: GenreDao) : FieldProducer {
    override fun getFieldDefinition(): GraphQLFieldDefinition {
        return GraphQLFieldDefinition.newFieldDefinition()
                .type(GraphQLList(GenreType().getGraphQlType()))
                .name("genres")
                .dataFetcher { _ -> dao.allMovieGenres }
                .build()
    }
}