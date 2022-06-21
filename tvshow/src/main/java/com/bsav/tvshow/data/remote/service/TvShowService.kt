package com.bsav.tvshow.data.remote.service

import com.bsav.core.utils.API_KEY
import com.bsav.tvshow.data.remote.model.TvShowNetwork
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TvShowService {

    @GET(ENDPOINT_TV_SHOW_DETAIL)
    suspend fun getTvShowById(
        @Path(ID) id: Int,
        @Query(KEY) apiKey: String = API_KEY
    ): TvShowNetwork
}

private const val ID = "id"
private const val KEY = "api_key"
private const val ENDPOINT_TV_SHOW_DETAIL = "tv/{$ID}"
