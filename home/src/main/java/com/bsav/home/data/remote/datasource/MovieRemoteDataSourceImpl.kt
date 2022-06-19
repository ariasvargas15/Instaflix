package com.bsav.home.data.remote.datasource;

import com.bsav.home.data.remote.service.MovieService
import com.bsav.home.domain.model.Program
import com.bsav.home.domain.model.ProgramType
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRemoteDataSource {

    override fun getPopularMovies(): Flow<List<Program>> = flow {
        emit(movieService.getPopularMovies().mapToDomain(ProgramType.Movie.Popular))
    }


    override fun getTopRatedMovies(): Flow<List<Program>> = flow {
        emit(movieService.getTopRatedMovies().mapToDomain(ProgramType.Movie.TopRated))
    }

}
