package ash.java.graphql.types.tvseason;

import ash.java.graphql.types.TmdbObjectType;
import com.google.gson.annotations.SerializedName;
import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLName;

import java.util.List;

@GraphQLName("TvEpisode")
public class TvEpisodeType extends TmdbObjectType {

    @GraphQLField
    @SerializedName("air_date")
    private String airDate;

    @GraphQLField
    private List<TvCrewType> crew;

    @GraphQLField
    @SerializedName("episode_number")
    private Integer episodeNumber;

    @GraphQLField
    @SerializedName("guest_stars")
    private List<TvGuestStarType> guestStars;

    @GraphQLField
    private String name;

    @GraphQLField
    private String overview;

    @GraphQLField
    @SerializedName("production_code")
    private String productionCode;

    @GraphQLField
    @SerializedName("season_number")
    private Integer seasonNumber;

    @GraphQLField
    @SerializedName("still_path")
    private String stillPath;

    @GraphQLField
    @SerializedName("vote_average")
    private Double voteAverage;

    @GraphQLField
    @SerializedName("vote_count")
    private Integer voteCount;

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
    }

    public List<TvCrewType> getCrew() {
        return crew;
    }

    public void setCrew(List<TvCrewType> crew) {
        this.crew = crew;
    }

    public Integer getEpisodeNumber() {
        return episodeNumber;
    }

    public void setEpisodeNumber(Integer episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public List<TvGuestStarType> getGuestStars() {
        return guestStars;
    }

    public void setGuestStars(List<TvGuestStarType> guestStars) {
        this.guestStars = guestStars;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getProductionCode() {
        return productionCode;
    }

    public void setProductionCode(String productionCode) {
        this.productionCode = productionCode;
    }

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public String getStillPath() {
        return stillPath;
    }

    public void setStillPath(String stillPath) {
        this.stillPath = stillPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }
}
