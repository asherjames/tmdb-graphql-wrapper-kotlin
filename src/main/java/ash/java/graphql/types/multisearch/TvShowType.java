package ash.java.graphql.types.multisearch;

import ash.java.graphql.types.TmdbObjectType;
import com.google.gson.annotations.SerializedName;
import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLName;

import java.util.List;

@GraphQLName("TvShow")
public class TvShowType extends TmdbObjectType {

    @GraphQLField
    private Double popularity;

    @GraphQLField
    private String overview;

    @GraphQLField
    private String name;

    @GraphQLField
    @SerializedName("poster_path")
    private String posterPath;

    @GraphQLField
    @SerializedName("backdrop_path")
    private String backdropPath;

    @GraphQLField
    @SerializedName("vote_average")
    private Double voteAverage;

    @GraphQLField
    @SerializedName("media_type")
    private String mediaType;

    @GraphQLField
    @SerializedName("first_air_date")
    private String firstAirDate;

    @GraphQLField
    @SerializedName("origin_country")
    private List<String> originCountry;

    @GraphQLField
    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @GraphQLField
    @SerializedName("original_language")
    private String originalLanguage;

    @GraphQLField
    @SerializedName("vote_count")
    private Integer voteCount;

    @GraphQLField
    @SerializedName("original_name")
    private String originalName;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public void setFirstAirDate(String firstAirDate) {
        this.firstAirDate = firstAirDate;
    }

    public List<String> getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(List<String> originCountry) {
        this.originCountry = originCountry;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(String originalName) {
        this.originalName = originalName;
    }

}
