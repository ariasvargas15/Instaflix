package com.bsav.home.data.remote.datasource

import com.bsav.home.data.remote.service.TvShowService
import com.bsav.home.domain.model.Program
import com.bsav.home.domain.model.ProgramType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvShowRemoteDataSourceImpl @Inject constructor(
    private val tvShowService: TvShowService
) : TvShowRemoteDataSource {

    override fun getPopularTvShows(): Flow<List<Program>> = flow {
        emit(tvShowService.getPopularTvShows().mapToDomain(ProgramType.TvShow.Popular))
    }

    override fun getTopRatedTvShows(): Flow<List<Program>> = flow {
        emit(tvShowService.getTopRatedTvShows().mapToDomain(ProgramType.TvShow.TopRated))
    }
}
