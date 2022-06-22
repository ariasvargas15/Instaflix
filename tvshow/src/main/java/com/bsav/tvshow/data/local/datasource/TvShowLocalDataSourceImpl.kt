package com.bsav.tvshow.data.local.datasource

import com.bsav.db.dao.TvShowDao
import com.bsav.tvshow.data.local.mapFromDomainToEntity
import com.bsav.tvshow.data.local.mapFromEntityToDomain
import com.bsav.tvshow.domain.model.TvShow
import javax.inject.Inject

class TvShowLocalDataSourceImpl @Inject constructor(
    private val tvShowDao: TvShowDao
) : TvShowLocalDataSource {

    override suspend fun getTvShowById(id: Int): TvShow {
        return tvShowDao.getTvShowById(id).mapFromEntityToDomain()
    }

    override suspend fun saveTvShow(tvShow: TvShow) {
        tvShowDao.saveTvShow(tvShow.mapFromDomainToEntity())
    }
}
