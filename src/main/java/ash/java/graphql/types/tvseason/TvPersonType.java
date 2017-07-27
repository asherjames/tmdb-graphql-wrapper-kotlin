package ash.java.graphql.types.tvseason;

import ash.java.graphql.types.TmdbObjectType;
import com.google.gson.annotations.SerializedName;
import graphql.annotations.GraphQLField;

public class TvPersonType extends TmdbObjectType {

    @GraphQLField
    private String name;

    @GraphQLField
    @SerializedName("credit_id")
    private String creditId;

    @GraphQLField
    @SerializedName("profile_path")
    private String profilePath;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public void setProfilePath(String profilePath) {
        this.profilePath = profilePath;
    }
}
