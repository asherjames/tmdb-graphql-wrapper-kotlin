package ash.kotlin.graphql.fields

import ash.kotlin.graphql.data.GenreDao
import ash.kotlin.graphql.types.genre.GenreType
import graphql.schema.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class GenreSchema @Autowired constructor(private val dao: GenreDao) : FieldProducer {
    override fun getFieldDefinition(): GraphQLFieldDefinition {
        return GraphQLFieldDefinition.newFieldDefinition()
                .type(GraphQLList(GenreType().getGraphQlType()))
                .name("genres")
                .dataFetcher { _ -> dao.getAllMovieGenres() }
                .build()
    }
}