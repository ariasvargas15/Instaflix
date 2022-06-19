package com.bsav.home.domain

import com.bsav.home.domain.model.Program
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<List<Program>>
    fun getTopRatedMovies(): Flow<List<Program>>
}