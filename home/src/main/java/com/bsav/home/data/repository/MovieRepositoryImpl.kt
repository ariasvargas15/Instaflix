package com.bsav.home.data.repository

import com.bsav.home.data.remote.datasource.MovieRemoteDataSource
import com.bsav.home.domain.MovieRepository
import com.bsav.home.domain.model.Program
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource
) : MovieRepository {
    override fun getPopularMovies(): Flow<List<Program>> {
        return movieRemoteDataSource.getPopularMovies()
    }

    override fun getTopRatedMovies(): Flow<List<Program>> {
        return movieRemoteDataSource.getTopRatedMovies()
    }
}
