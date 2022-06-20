package com.bsav.home.data.remote.datasource

import com.bsav.home.data.remote.model.PageTvShowNetwork
import com.bsav.home.data.remote.model.TvShowNetwork
import com.bsav.home.data.remote.service.TvShowService
import com.bsav.home.domain.model.Program
import com.bsav.home.domain.model.ProgramType
import io.mockk.coEvery
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
    fun `when getPopularTvShows is called then should call service and emit the result`() {
        val service = PageTvShowNetwork(
            listOf(TvShowNetwork(1, "title", "path"))
        )
        coEvery { tvShowService.getPopularTvShows() } answers { service }
        val expected = listOf(Program(1, "title", "path", ProgramType.TvShow.Popular))

        val response = runBlocking {
            tvShowRemoteDataSource.getPopularTvShows().first()
        }
        Assert.assertEquals(expected, response)
    }

    @Test
    fun `when getTopRatedTvShows is called then should call service and emit the result`() {
        val service = PageTvShowNetwork(
            listOf(TvShowNetwork(1, "title", "path"))
        )
        coEvery { tvShowService.getTopRatedTvShows() } answers { service }
        val expected = listOf(Program(1, "title", "path", ProgramType.TvShow.TopRated))

        val response = runBlocking {
            tvShowRemoteDataSource.getTopRatedTvShows().first()
        }
        Assert.assertEquals(expected, response)
    }
}
