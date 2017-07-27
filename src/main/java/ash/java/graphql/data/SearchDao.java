package ash.java.graphql.data;

import ash.java.graphql.types.movie.MovieType;

import java.util.List;
import java.util.Map;

public interface SearchDao {

    List<MovieType> searchMoviesWithQuery(String query);

    List<MovieType> searchMoviesWithMultipleParameters(Map<String, Object> params);

    List<Object> searchMultiSearch(Map<String, Object> params);
}
