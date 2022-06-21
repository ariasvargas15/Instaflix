package com.bsav.movie.data.remote.service

import com.bsav.core.utils.API_KEY
import com.bsav.movie.data.remote.model.MovieNetwork
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieService {

    @GET(ENDPOINT_MOVIE_DETAIL)
    suspend fun getMovieById(
        @Path(ID) id: Int,
        @Query(KEY) apiKey: String = API_KEY
    ): MovieNetwork
}

private const val ID = "id"
private const val KEY = "api_key"
private const val ENDPOINT_MOVIE_DETAIL = "movie/{$ID}"
