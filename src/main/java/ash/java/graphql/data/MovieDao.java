package ash.java.graphql.data;

import ash.java.graphql.types.keyword.KeywordType;

import java.util.List;

public interface MovieDao {

    List<KeywordType> getKeywordsForMovie(int movieId);
}
