package ash.java.graphql.data;

import ash.java.graphql.types.genre.GenreType;

import java.util.List;

public interface GenreDao {

    List<GenreType> getAllMovieGenres();
}
