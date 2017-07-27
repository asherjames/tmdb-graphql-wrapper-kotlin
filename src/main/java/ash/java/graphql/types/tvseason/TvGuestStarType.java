package ash.java.graphql.types.tvseason;

import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLName;

@GraphQLName("TvGuestStar")
public class TvGuestStarType extends TvPersonType {

    @GraphQLField
    private String character;

    @GraphQLField
    private Integer order;

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
