package com.bsav.tvshow.data.local.datasource

import com.bsav.tvshow.domain.model.TvShow

interface TvShowLocalDataSource {
    suspend fun getTvShowById(id: Int): TvShow
    suspend fun saveTvShow(tvShow: TvShow)
}
