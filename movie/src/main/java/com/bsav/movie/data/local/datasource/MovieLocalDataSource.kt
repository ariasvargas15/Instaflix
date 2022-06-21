package com.bsav.movie.data.local.datasource

import com.bsav.movie.domain.model.Movie

interface MovieLocalDataSource {
    suspend fun getMovieById(id: Int): Movie
    suspend fun saveMovie(movie: Movie)
}
