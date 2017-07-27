package ash.java.graphql.types;

import graphql.annotations.GraphQLAnnotations;
import graphql.annotations.GraphQLField;
import graphql.schema.GraphQLObjectType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class TmdbObjectType {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

    @GraphQLField
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public GraphQLObjectType getGraphQlType() {
        GraphQLObjectType objectType = null;
        try {
            objectType = GraphQLAnnotations.object(this.getClass());
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException e) {
            log.error("Exception while trying to produce GraphQL type for class", e);
        }

        return objectType;
    }
}
