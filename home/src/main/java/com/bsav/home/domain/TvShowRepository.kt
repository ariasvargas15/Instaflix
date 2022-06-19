package com.bsav.home.domain

import com.bsav.home.domain.model.Program
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {
    fun getPopularTvShows(): Flow<List<Program>>
    fun getTopRatedTvShows(): Flow<List<Program>>
}
