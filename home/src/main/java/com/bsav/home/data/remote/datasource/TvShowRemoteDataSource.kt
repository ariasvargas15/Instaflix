package com.bsav.home.data.remote.datasource

import com.bsav.home.domain.model.Program
import kotlinx.coroutines.flow.Flow

interface TvShowRemoteDataSource {
    fun getPopularTvShows(): Flow<List<Program>>
    fun getTopRatedTvShows(): Flow<List<Program>>
}
