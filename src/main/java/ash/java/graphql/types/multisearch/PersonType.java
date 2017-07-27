package ash.java.graphql.types.multisearch;

import ash.java.graphql.types.TmdbObjectType;
import com.google.gson.annotations.SerializedName;
import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLName;
import graphql.annotations.GraphQLType;

@GraphQLName("Person")
public class PersonType extends TmdbObjectType {

    @GraphQLField
    private Boolean adult;

    @GraphQLField
    private String name;

    @GraphQLField
    private Double popularity;

    @GraphQLField
    @SerializedName("profile_path")
    private String profilePath;

    @GraphQLField
    @SerializedName("media_type")
    private String mediaType;

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
