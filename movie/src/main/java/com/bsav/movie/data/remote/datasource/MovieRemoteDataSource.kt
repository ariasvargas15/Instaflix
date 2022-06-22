package com.bsav.movie.data.remote.datasource

import com.bsav.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    fun getMovieById(id: Int): Flow<Movie>
}
