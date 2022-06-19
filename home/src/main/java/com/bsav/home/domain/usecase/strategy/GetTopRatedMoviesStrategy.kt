package com.bsav.home.domain.usecase.strategy

import com.bsav.home.domain.MovieRepository
import com.bsav.home.domain.model.Program
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopRatedMoviesStrategy @Inject constructor(
    private val movieRepository: MovieRepository
) : GetProgramsStrategy {
    override fun execute(): Flow<List<Program>> {
        return movieRepository.getTopRatedMovies()
    }
}
