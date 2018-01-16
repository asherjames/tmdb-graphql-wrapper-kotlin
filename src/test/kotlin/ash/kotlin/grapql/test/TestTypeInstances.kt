package ash.kotlin.grapql.test

import ash.kotlin.graphql.types.movie.MovieType
import ash.kotlin.graphql.types.multisearch.PersonType
import ash.kotlin.graphql.types.multisearch.TvShowType
import ash.kotlin.graphql.types.tvseason.TvSeasonType
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*

fun extractData(input: Any): JsonObject
{
    return Gson().toJsonTree(input).asJsonObject["data"].asJsonObject
}

fun extractError(input: Any): JsonArray
{
    return Gson().toJsonTree(input).asJsonObject["errors"].asJsonArray
}

fun loadTestResource(filename: String): String
{
    val url = Thread.currentThread().contextClassLoader.getResource(filename)

    val lines: List<String>

    lines = Files.readAllLines(Paths.get(url.toURI()))

    return lines.reduce { a, b -> a + b }
}

fun getMovie(): MovieType
{
    val movie = MovieType(387)
    movie.posterPath = "/kI1rptTkqDWj6SBRsYwguBvPViT.jpg"
    movie.adult = false
    movie.overview = "A German submarine hunts allied ships..."
    movie.releaseDate = "1981-09-16"
    movie.genreIds = Arrays.asList(28, 18, 36, 10752, 12)
    movie.originalTitle = "Das Boot"
    movie.originalLanguage = "de"
    movie.title = "Das Boot"
    movie.backdropPath = "/mc0PbKrrFRCUEpI09reR3ihHrIo.jpg"
    movie.popularity = 3.495501
    movie.voteCount = 501
    movie.video = false
    movie.voteAverage = 7.9

    return movie
}

fun getPerson(): PersonType
{
    val person = PersonType(10205)
    person.profilePath = "/wlg55BTcp3kqfTb3zDtqOFyqhDR.jpg"
    person.adult = false
    person.mediaType = "person"
    person.name = "Sigourney Weaver"
    person.popularity = 8.978738

    return person
}

fun getTvShow(): TvShowType
{
    val tvShowType = TvShowType(1408)
    tvShowType.posterPath = "/lxSzRZ49NXwsiyHuvMsd19QxduC.jpg"
    tvShowType.popularity = 14.202559
    tvShowType.overview = "Dr. Gregory House, a drug-addicted, unconventional, misanthropic medical genius..."
    tvShowType.backdropPath = "/6r5o8yvLx7nESRBC1iMuYBCk9Cj.jpg"
    tvShowType.voteAverage = 7.89
    tvShowType.mediaType = "tv"
    tvShowType.firstAirDate = "2004-11-16"
    tvShowType.originCountry = Arrays.asList("US")
    tvShowType.genreIds = Arrays.asList(18, 35, 9648)
    tvShowType.originalLanguage = "en"
    tvShowType.voteCount = 418
    tvShowType.name = "House"
    tvShowType.originalName = "House"

    return tvShowType
}

fun getTvSeason(): TvSeasonType
{
    val tvSeasonType = object : TypeToken<TvSeasonType>()
    {

    }.type
    val jsonString = loadTestResource("house_season_5.json")

    return Gson().fromJson(jsonString, tvSeasonType)
}