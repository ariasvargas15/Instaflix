package com.bsav.tvshow.data.repository

import com.bsav.tvshow.data.local.datasource.TvShowLocalDataSource
import com.bsav.tvshow.data.remote.datasource.TvShowRemoteDataSource
import com.bsav.tvshow.domain.TvShowRepository
import com.bsav.tvshow.domain.model.TvShow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val tvShowLocalDataSource: TvShowLocalDataSource,
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
) : TvShowRepository {

    override fun getTvShowById(id: Int): Flow<TvShow> {
        return tvShowRemoteDataSource
            .getTvShowById(id)
            .onEach { saveTvShowInCache(it) }
            .catch { emit(getCachedTvShowById(id)) }
    }

    private suspend fun saveTvShowInCache(tvShow: TvShow) {
        tvShowLocalDataSource.saveTvShow(tvShow)
    }

    private suspend fun getCachedTvShowById(id: Int): TvShow {
        return tvShowLocalDataSource.getTvShowById(id)
    }
}
