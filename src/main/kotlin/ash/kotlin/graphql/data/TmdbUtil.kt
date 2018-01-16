package ash.kotlin.graphql.data

import ash.kotlin.graphql.AppConfig
import ash.kotlin.graphql.TmdbGqlException
import ash.kotlin.graphql.types.genre.GenreType
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.gson.responseObject
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import javax.inject.Inject

class TmdbUtil @Inject constructor(config: AppConfig)
{
    init
    {
        FuelManager.instance.basePath = config.baseUrl
    }

    fun getGenreList(): List<GenreType>
    {
        var data: List<GenreType> = emptyList()

        "/genre/movie/list".httpGet().responseObject<List<GenreType>> {_, _, result ->
            when (result) {
                is Result.Failure -> throw TmdbGqlException(result.error)
                is Result.Success -> data = result.get()
            }
        }

        return data
    }
}