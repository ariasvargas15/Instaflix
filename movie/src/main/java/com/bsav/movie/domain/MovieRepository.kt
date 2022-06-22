package com.bsav.movie.domain

import com.bsav.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getMovieById(id: Int): Flow<Movie>
}
