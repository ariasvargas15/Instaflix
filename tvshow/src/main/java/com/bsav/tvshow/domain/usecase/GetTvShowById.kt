package com.bsav.tvshow.domain.usecase

import com.bsav.tvshow.domain.TvShowRepository
import com.bsav.tvshow.domain.model.TvShow
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTvShowById @Inject constructor(
    private val tvShowRepository: TvShowRepository
) {
    operator fun invoke(id: Int): Flow<TvShow> {
        return tvShowRepository.getTvShowById(id)
    }
}
