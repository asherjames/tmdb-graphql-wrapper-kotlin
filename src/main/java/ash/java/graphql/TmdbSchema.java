package ash.java.graphql;

import ash.java.graphql.fields.FieldProducer;
import graphql.GraphQL;
import graphql.schema.GraphQLFieldDefinition;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static graphql.schema.GraphQLObjectType.newObject;

@Service
public class TmdbSchema {

    private GraphQL graphQL;
    private static Logger log = LoggerFactory.getLogger(TmdbSchema.class);

    @Autowired
    public TmdbSchema(List<FieldProducer> tmdbFields) {
        List<GraphQLFieldDefinition> fieldDefinitions = tmdbFields.stream()
                .map(FieldProducer::getFieldDefinition)
                .collect(Collectors.toList());

        GraphQLObjectType queryType = newObject()
                .name("QueryType")
                .fields(fieldDefinitions)
                .build();

        GraphQLSchema tmdbSchema = GraphQLSchema.newSchema()
                .query(queryType)
                .build();

        graphQL = new GraphQL(tmdbSchema);
    }

    public Object executeQuery(String query) {
        log.info("Executing query: {}", query);
        return graphQL.execute(query);
    }
}
