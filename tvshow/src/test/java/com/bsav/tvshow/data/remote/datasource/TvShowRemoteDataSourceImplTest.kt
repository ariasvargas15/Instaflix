package com.bsav.tvshow.data.remote.datasource

import com.bsav.tvshow.data.remote.model.TvShowNetwork
import com.bsav.tvshow.data.remote.service.TvShowService
import com.bsav.tvshow.domain.model.TvShow
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TvShowRemoteDataSourceImplTest {

    private val tvShowService = mockk<TvShowService>()
    private lateinit var tvShowRemoteDataSource: TvShowRemoteDataSource

    @Before
    fun setup() {
        tvShowRemoteDataSource = TvShowRemoteDataSourceImpl(tvShowService)
    }

    @Test
    fun `given an id when getTvShowById is called then should call service and emit flow`() {
        val tvShowNetwork = mockk<TvShowNetwork>()
        val tvShow = mockk<TvShow>()
        coEvery { tvShowService.getTvShowById(1) } answers { tvShowNetwork }
        every { tvShowNetwork.mapToDomain() } answers { tvShow }

        val response = runBlocking {
            tvShowRemoteDataSource.getTvShowById(1).first()
        }

        coVerify(exactly = 1) {
            tvShowService.getTvShowById(1)
        }
        Assert.assertEquals(tvShow, response)
    }
}
