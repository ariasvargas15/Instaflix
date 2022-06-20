package com.bsav.home.data.repository

import com.bsav.home.data.local.datasource.ProgramLocalDataSource
import com.bsav.home.data.remote.datasource.TvShowRemoteDataSource
import com.bsav.home.domain.TvShowRepository
import com.bsav.home.domain.model.Program
import com.bsav.home.domain.model.ProgramType
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

    private val tvShowRemoteDataSource = mockk<TvShowRemoteDataSource>()
    private val programLocalDataSource = mockk<ProgramLocalDataSource>(relaxed = true)
    private lateinit var tvShowRepository: TvShowRepository

    @Before
    fun setup() {
        tvShowRepository = TvShowRepositoryImpl(tvShowRemoteDataSource, programLocalDataSource)
    }

    @Test
    fun `given success when getPopularTvShows is called then should save locally and return flow`() {
        val expected = listOf(Program(1, "", "", ProgramType.TvShow.Popular))
        every { tvShowRemoteDataSource.getPopularTvShows() } answers { flowOf(expected) }

        val response = runBlocking {
            tvShowRepository.getPopularTvShows().first()
        }

        Assert.assertEquals(expected, response)
        coVerify(exactly = 1) {
            tvShowRemoteDataSource.getPopularTvShows()
            programLocalDataSource.savePrograms(expected)
            programLocalDataSource.deleteProgramsByType(ProgramType.TvShow.Popular)
        }
        coVerify(exactly = 0) {
            programLocalDataSource.getProgramsByType(any())
        }
    }

    @Test
    fun `given exception when getPopularTvShows is called then should get local data and emit it`() {
        val expected = mockk<List<Program>>()
        every { tvShowRemoteDataSource.getPopularTvShows() } answers { flow { throw Exception() } }
        coEvery { programLocalDataSource.getProgramsByType(ProgramType.TvShow.Popular) } answers { expected }

        val response = runBlocking {
            tvShowRepository.getPopularTvShows().first()
        }

        Assert.assertEquals(expected, response)
        coVerify(exactly = 1) {
            tvShowRemoteDataSource.getPopularTvShows()
            programLocalDataSource.getProgramsByType(ProgramType.TvShow.Popular)
        }
        coVerify(exactly = 0) {
            programLocalDataSource.savePrograms(any())
            programLocalDataSource.deleteProgramsByType(any())
        }
    }

    @Test
    fun `given success when getTopRatedTvShows is called then should save locally and return flow`() {
        val expected = listOf(Program(1, "", "", ProgramType.TvShow.TopRated))
        every { tvShowRemoteDataSource.getTopRatedTvShows() } answers { flowOf(expected) }

        val response = runBlocking {
            tvShowRepository.getTopRatedTvShows().first()
        }

        Assert.assertEquals(expected, response)
        coVerify(exactly = 1) {
            tvShowRemoteDataSource.getTopRatedTvShows()
            programLocalDataSource.savePrograms(expected)
            programLocalDataSource.deleteProgramsByType(ProgramType.TvShow.TopRated)
        }
        coVerify(exactly = 0) {
            programLocalDataSource.getProgramsByType(any())
        }
    }

    @Test
    fun `given exception when getTopRatedTvShows is called then should get local data and emit it`() {
        val expected = mockk<List<Program>>()
        every { tvShowRemoteDataSource.getTopRatedTvShows() } answers { flow { throw Exception() } }
        coEvery { programLocalDataSource.getProgramsByType(ProgramType.TvShow.TopRated) } answers { expected }

        val response = runBlocking {
            tvShowRepository.getTopRatedTvShows().first()
        }

        Assert.assertEquals(expected, response)
        coVerify(exactly = 1) {
            tvShowRemoteDataSource.getTopRatedTvShows()
            programLocalDataSource.getProgramsByType(ProgramType.TvShow.TopRated)
        }
        coVerify(exactly = 0) {
            programLocalDataSource.savePrograms(any())
            programLocalDataSource.deleteProgramsByType(any())
        }
    }
}
