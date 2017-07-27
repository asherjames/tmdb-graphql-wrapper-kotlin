package ash.java.graphql;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApiKeyManager {
    private static ApiKeyManager ourInstance = new ApiKeyManager();
    private static String apiKeyString;
    private Logger log = LoggerFactory.getLogger(ApiKeyManager.class);
    private Properties props = new Properties();

    private ApiKeyManager() {
        try {
            log.info("Attempting to load API key from properties file...");
            InputStream propertiesFile = this.getClass().getClassLoader().getResourceAsStream("apiKey.properties");
            if(propertiesFile == null) {
                throw new IOException("Could not find properties file!");
            }
            props.load(propertiesFile);
            apiKeyString = props.getProperty("apikey");
        } catch (IOException e) {
            log.info("Attempting to load API key from environment variables...", e);
            apiKeyString = System.getenv("TMDB_API_KEY");
            if(apiKeyString == null) {
                throw new TmdbGqlException("Could not load API key from environment variable!");
            }
        }
    }

    public static String getKey() {
        return apiKeyString;
    }
}
