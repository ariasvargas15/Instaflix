package com.bsav.home.data.repository

import com.bsav.home.data.local.datasource.ProgramLocalDataSource
import com.bsav.home.data.remote.datasource.TvShowRemoteDataSource
import com.bsav.home.domain.TvShowRepository
import com.bsav.home.domain.model.Program
import com.bsav.home.domain.model.ProgramType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val tvShowRemoteDataSource: TvShowRemoteDataSource,
    private val programLocalDataSource: ProgramLocalDataSource,
) : TvShowRepository {
    override fun getPopularTvShows(): Flow<List<Program>> =
        tvShowRemoteDataSource
            .getPopularTvShows()
            .onEach { saveMoviesInCache(it) }
            .catch { emit(getMoviesInCacheByType(ProgramType.TvShow.Popular)) }

    override fun getTopRatedTvShows(): Flow<List<Program>> =
        tvShowRemoteDataSource
            .getTopRatedTvShows()
            .onEach { saveMoviesInCache(it) }
            .catch { emit(getMoviesInCacheByType(ProgramType.TvShow.TopRated)) }

    private suspend fun saveMoviesInCache(programs: List<Program>) {
        programLocalDataSource.savePrograms(programs)
    }

    private fun getMoviesInCacheByType(type: ProgramType): List<Program> =
        programLocalDataSource.getProgramsByType(type)
}
