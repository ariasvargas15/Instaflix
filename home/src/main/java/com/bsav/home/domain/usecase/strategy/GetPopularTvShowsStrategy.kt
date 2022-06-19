package com.bsav.home.domain.usecase.strategy

import com.bsav.home.domain.TvShowRepository
import com.bsav.home.domain.model.Program
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetPopularTvShowsStrategy @Inject constructor(
    private val tvShowRepository: TvShowRepository
) : GetProgramsStrategy {
    override fun execute(): Flow<List<Program>> {
        return tvShowRepository.getPopularTvShows()
    }
}