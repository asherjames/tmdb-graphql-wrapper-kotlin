package ash.java.graphql.data;

public final class TmdbUrls {
    private static final String BASE_URL = "https://api.themoviedb.org/3";

    public enum TmdbUrl {
        GENRE_LIST_URL(BASE_URL + "/genre/movie/list");

        final String url;

        TmdbUrl(String url) {
            this.url = url;
        }
    }

    public enum TmdbArgUrl {
        MOVIE_KEYWORDS_URL(BASE_URL + "/movie/", "/keywords");

        final String base;
        final String firstPart;

        TmdbArgUrl(String base, String firstPart) {
            this.base = base;
            this.firstPart = firstPart;
        }
    }

    public enum TmdbTwoArgUrl {
        TV_SEASON_URL(BASE_URL + "/tv/", "/season/");

        final String base;
        final String firstPart;

        TmdbTwoArgUrl(String base, String firstPart) {
            this.base = base;
            this.firstPart = firstPart;
        }
    }

    public enum TmdbQueryUrl {
        MOVIE_SEARCH_URL(BASE_URL + "/search/movie"),
        MULTI_SEARCH_URL(BASE_URL + "/search/multi");

        final String url;

        TmdbQueryUrl(String url) {
            this.url = url;
        }
    }
}
