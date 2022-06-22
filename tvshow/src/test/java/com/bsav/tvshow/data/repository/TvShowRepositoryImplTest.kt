package com.bsav.tvshow.data.repository

import com.bsav.tvshow.data.local.datasource.TvShowLocalDataSource
import com.bsav.tvshow.data.remote.datasource.TvShowRemoteDataSource
import com.bsav.tvshow.domain.TvShowRepository
import com.bsav.tvshow.domain.model.TvShow
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TvShowRepositoryImplTest {

    private val tvShowLocalDataSource = mockk<TvShowLocalDataSource>(relaxed = true)
    private val tvShowRemoteDataSource = mockk<TvShowRemoteDataSource>()
    private lateinit var tvShowRepository: TvShowRepository

    @Before
    fun setup() {
        tvShowRepository = TvShowRepositoryImpl(tvShowLocalDataSource, tvShowRemoteDataSource)
    }

    @Test
    fun `given success when getTvShowById is called then should save locally and return flow`() {
        val expected = mockk<TvShow>()
        every { tvShowRemoteDataSource.getTvShowById(1) } answers { flowOf(expected) }

        val response = runBlocking {
            tvShowRepository.getTvShowById(1).first()
        }

        Assert.assertEquals(expected, response)
        coVerify(exactly = 1) {
            tvShowRemoteDataSource.getTvShowById(1)
            tvShowLocalDataSource.saveTvShow(expected)
        }
        coVerify(exactly = 0) {
            tvShowLocalDataSource.getTvShowById(any())
        }
    }

    @Test
    fun `given exception when getTvShowById is called then should get local data and emit it`() {
        val expected = mockk<TvShow>()
        every { tvShowRemoteDataSource.getTvShowById(1) } answers { flow { throw Exception() } }
        coEvery { tvShowLocalDataSource.getTvShowById(1) } answers { expected }

        val response = runBlocking {
            tvShowRepository.getTvShowById(1).first()
        }

        Assert.assertEquals(expected, response)
        coVerify(exactly = 1) {
            tvShowRemoteDataSource.getTvShowById(1)
            tvShowLocalDataSource.getTvShowById(1)
        }
        coVerify(exactly = 0) {
            tvShowLocalDataSource.saveTvShow(any())
        }
    }
}
