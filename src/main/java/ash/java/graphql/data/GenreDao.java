package ash.java.graphql.data;

import ash.kotlin.graphql.GenreType;

import java.util.List;

public interface GenreDao {

    List<GenreType> getAllMovieGenres();
}
