package com.bsav.home.data.repository

import com.bsav.home.data.remote.datasource.TvShowRemoteDataSource
import com.bsav.home.domain.TvShowRepository
import com.bsav.home.domain.model.Program
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class TvShowRepositoryImpl @Inject constructor(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource
) : TvShowRepository {
    override fun getPopularTvShows(): Flow<List<Program>> {
        return tvShowRemoteDataSource.getPopularTvShows()
    }

    override fun getTopRatedTvShows(): Flow<List<Program>> {
        return tvShowRemoteDataSource.getTopRatedTvShows()
    }
}