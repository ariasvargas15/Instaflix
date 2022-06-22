package com.bsav.tvshow.data.remote.datasource

import com.bsav.tvshow.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowRemoteDataSource {
    fun getTvShowById(id: Int): Flow<TvShow>
}
