package com.bsav.home.data.remote.service

import com.bsav.core.utils.API_KEY
import com.bsav.home.data.remote.model.PageTvShowNetwork
import retrofit2.http.GET
import retrofit2.http.Query

interface TvShowService {

    @GET(ENDPOINT_POPULAR)
    suspend fun getPopularTvShows(
        @Query(PAGE) page: Int = FIRST_PAGE,
        @Query(KEY) apiKey: String = API_KEY
    ): PageTvShowNetwork

    @GET(ENDPOINT_TOP_RATED)
    suspend fun getTopRatedTvShows(
        @Query(PAGE) page: Int = FIRST_PAGE,
        @Query(KEY) apiKey: String = API_KEY
    ): PageTvShowNetwork
}

private const val FIRST_PAGE = 1
private const val PAGE = "page"
private const val KEY = "api_key"
private const val ENDPOINT_POPULAR = "tv/popular"
private const val ENDPOINT_TOP_RATED = "tv/top_rated"
