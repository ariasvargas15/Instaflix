package com.bsav.movie.data.repository

import com.bsav.movie.data.local.datasource.MovieLocalDataSource
import com.bsav.movie.data.remote.datasource.MovieRemoteDataSource
import com.bsav.movie.domain.MovieRepository
import com.bsav.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieRemoteDataSource: MovieRemoteDataSource,
) : MovieRepository {

    override fun getMovieById(id: Int): Flow<Movie> {
        return movieRemoteDataSource
            .getMovieById(id)
            .onEach { saveMovieInCache(it) }
            .catch { emit(getCachedMovieById(id)) }
    }

    private suspend fun saveMovieInCache(movie: Movie) {
        movieLocalDataSource.saveMovie(movie)
    }

    private suspend fun getCachedMovieById(id: Int): Movie {
        return movieLocalDataSource.getMovieById(id)
    }
}
