package ash.kotlin.graphql

import com.fasterxml.jackson.annotation.JsonProperty
import io.dropwizard.Configuration
import org.hibernate.validator.constraints.NotEmpty

data class AppConfig(@JsonProperty val apikey: String = "",
                     @JsonProperty val baseUrl: String = "") : Configuration()