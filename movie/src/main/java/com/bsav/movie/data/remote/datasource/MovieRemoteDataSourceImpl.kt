package com.bsav.movie.data.remote.datasource

import com.bsav.movie.data.remote.service.MovieService
import com.bsav.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieRemoteDataSourceImpl @Inject constructor(
    private val movieService: MovieService
) : MovieRemoteDataSource {

    override fun getMovieById(id: Int): Flow<Movie> = flow {
        movieService
            .getMovieById(id)
            .mapToDomain()
            .let {
                emit(it)
            }
    }
}
