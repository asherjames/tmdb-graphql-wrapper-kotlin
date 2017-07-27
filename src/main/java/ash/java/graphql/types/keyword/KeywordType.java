package ash.java.graphql.types.keyword;

import ash.java.graphql.types.TmdbObjectType;
import graphql.annotations.GraphQLField;

public class KeywordType extends TmdbObjectType {

    @GraphQLField
    private String name;

    public KeywordType() {}

    public KeywordType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
