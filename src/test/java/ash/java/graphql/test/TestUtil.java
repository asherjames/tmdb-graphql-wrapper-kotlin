package ash.java.graphql.test;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestUtil {

    private static Gson gson = new Gson();
    private static Logger log = LoggerFactory.getLogger(TestUtil.class);

    public static JsonObject extractData(Object input) {
        return gson.toJsonTree(input).getAsJsonObject().get("data").getAsJsonObject();
    }

    public static JsonArray extractError(Object input) {
        return gson.toJsonTree(input).getAsJsonObject().get("errors").getAsJsonArray();
    }

    public static String loadTestResource(String filename) {
        URL url = TestUtil.class.getClassLoader().getResource(filename);

        List<String> lines;

        try {
            lines = Files.readAllLines(Paths.get(url.toURI()));
        } catch (URISyntaxException e) {
            log.error("URI syntax error", e);
            return "";
        } catch (IOException e) {
            log.error("IO error", e);
            return "";
        }

        return lines.stream().reduce("", String::concat);
    }
}
