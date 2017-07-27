package ash.java.graphql.types.movie;

import ash.java.graphql.types.TmdbObjectType;
import com.google.gson.annotations.SerializedName;
import graphql.annotations.GraphQLField;
import graphql.annotations.GraphQLName;

import java.util.List;

@GraphQLName("Movie")
public class MovieType extends TmdbObjectType {

    @GraphQLField
    private String title;

    @GraphQLField
    private Double popularity;

    @GraphQLField
    private Boolean video;

    @GraphQLField
    private Boolean adult;

    @GraphQLField
    private String overview;

    @GraphQLField
    @SerializedName("poster_path")
    private String posterPath;

    @GraphQLField
    @SerializedName("release_date")
    private String releaseDate;

    @GraphQLField
    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @GraphQLField
    @SerializedName("original_title")
    private String originalTitle;

    @GraphQLField
    @SerializedName("original_language")
    private String originalLanguage;

    @GraphQLField
    @SerializedName("backdrop_path")
    private String backdropPath;

    @GraphQLField
    @SerializedName("vote_count")
    private Integer voteCount;

    @GraphQLField
    @SerializedName("vote_average")
    private Double voteAverage;

    @GraphQLField
    @SerializedName("media_type")
    private String mediaType;

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreId) {
        this.genreIds = genreId;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    public String getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }
}
