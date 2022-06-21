package com.bsav.movie.domain.usecase

import com.bsav.movie.domain.MovieRepository
import com.bsav.movie.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMovieById @Inject constructor(
    private val movieRepository: MovieRepository
) {
    operator fun invoke(id: Int): Flow<Movie> {
        return movieRepository.getMovieById(id)
    }
}
