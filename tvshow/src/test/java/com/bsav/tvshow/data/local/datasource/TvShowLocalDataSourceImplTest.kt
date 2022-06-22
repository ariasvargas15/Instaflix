package com.bsav.tvshow.data.local.datasource

import com.bsav.db.dao.TvShowDao
import com.bsav.db.entity.TvShowEntity
import com.bsav.tvshow.data.local.mapFromDomainToEntity
import com.bsav.tvshow.data.local.mapFromEntityToDomain
import com.bsav.tvshow.domain.model.TvShow
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.runs
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TvShowLocalDataSourceImplTest {

    private val tvShowDao = mockk<TvShowDao>()
    private lateinit var tvShowLocalDataSource: TvShowLocalDataSource

    @Before
    fun setup() {
        tvShowLocalDataSource = TvShowLocalDataSourceImpl(tvShowDao)
    }

    @Test
    fun `when getTvShowById is called then should call dao`() {
        val entity = mockk<TvShowEntity>()
        val expected = mockk<TvShow>()
        mockkStatic(TvShowEntity::mapFromEntityToDomain)
        coEvery { tvShowDao.getTvShowById(1) } answers { entity }
        every { entity.mapFromEntityToDomain() } answers { expected }

        val response = runBlocking {
            tvShowLocalDataSource.getTvShowById(1)
        }

        coVerify(exactly = 1) {
            tvShowDao.getTvShowById(1)
        }
        Assert.assertEquals(expected, response)
    }

    @Test
    fun `when saveTvShow is called then should call dao`() {
        val entity = mockk<TvShowEntity>()
        val tvShow = mockk<TvShow>()
        mockkStatic(TvShow::mapFromDomainToEntity)
        every { tvShow.mapFromDomainToEntity() } answers { entity }
        coEvery { tvShowDao.saveTvShow(entity) } just runs

        runBlocking {
            tvShowLocalDataSource.saveTvShow(tvShow)
        }

        coVerify(exactly = 1) {
            tvShowDao.saveTvShow(entity)
        }
    }
}
