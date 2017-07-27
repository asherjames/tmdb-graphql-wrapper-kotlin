package ash.java.graphql;

public class TmdbGqlException extends RuntimeException {
    public TmdbGqlException(String message) {
        super(message);
    }

    public TmdbGqlException(Throwable throwable) {
        super(throwable);
    }

    public TmdbGqlException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
