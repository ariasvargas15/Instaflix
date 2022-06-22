package com.bsav.tvshow.data.remote.datasource

import com.bsav.tvshow.data.remote.service.TvShowService
import com.bsav.tvshow.domain.model.TvShow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TvShowRemoteDataSourceImpl @Inject constructor(
    private val tvShowService: TvShowService
) : TvShowRemoteDataSource {

    override fun getTvShowById(id: Int): Flow<TvShow> = flow {
        try {
            tvShowService
                .getTvShowById(id)
                .mapToDomain()
                .let {
                    emit(it)
                }
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }
}
