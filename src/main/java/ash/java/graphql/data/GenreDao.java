package ash.java.graphql.data;

import ash.kotlin.graphql.types.genre.GenreType;

import java.util.List;

public interface GenreDao {

    List<GenreType> getAllMovieGenres();
}
