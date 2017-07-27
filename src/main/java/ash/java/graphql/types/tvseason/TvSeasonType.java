package ash.java.graphql.types.tvseason;

import ash.java.graphql.types.TmdbObjectType;
import com.google.gson.annotations.SerializedName;
import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLName;

import java.util.List;

@GraphQLName("TvSeason")
public class TvSeasonType extends TmdbObjectType {

    @GraphQLField
    @SerializedName("air_date")
    private String airDate;

    @GraphQLField
    private List<TvEpisodeType> episodes;

    @GraphQLField
    private String name;

    @GraphQLField
    private String overview;

    @GraphQLField
    @SerializedName("poster_path")
    private String posterPath;

    @GraphQLField
    @SerializedName("season_number")
    private Integer seasonNumber;

    public String getAirDate() {
        return airDate;
    }

    public void setAirDate(String airDate) {
        this.airDate = airDate;
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

    public Integer getSeasonNumber() {
        return seasonNumber;
    }

    public void setSeasonNumber(Integer seasonNumber) {
        this.seasonNumber = seasonNumber;
    }

    public List<TvEpisodeType> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<TvEpisodeType> episodes) {
        this.episodes = episodes;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
