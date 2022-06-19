package com.bsav.home.data.remote.datasource

import com.bsav.home.domain.model.Program
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    fun getPopularMovies(): Flow<List<Program>>
    fun getTopRatedMovies(): Flow<List<Program>>
}