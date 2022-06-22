package com.bsav.tvshow.domain

import com.bsav.tvshow.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {
    fun getTvShowById(id: Int): Flow<TvShow>
}
