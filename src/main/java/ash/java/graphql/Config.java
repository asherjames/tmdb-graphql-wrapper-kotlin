package ash.java.graphql;

import ash.java.graphql.api.Endpoints;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "ash.kotlin")
public class Config extends ResourceConfig {
    public Config() {
        register(Endpoints.class);
    }
}
