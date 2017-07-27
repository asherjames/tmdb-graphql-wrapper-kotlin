package ash.java.graphql.fields;

import graphql.schema.GraphQLFieldDefinition;

public interface FieldProducer {

    GraphQLFieldDefinition getFieldDefinition();
}
