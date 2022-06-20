package com.bsav.home.data.repository

import com.bsav.home.data.local.datasource.ProgramLocalDataSource
import com.bsav.home.data.remote.datasource.MovieRemoteDataSource
import com.bsav.home.domain.MovieRepository
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

class MovieRepositoryImplTest {

    private val movieRemoteDataSource = mockk<MovieRemoteDataSource>()
    private val programLocalDataSource = mockk<ProgramLocalDataSource>(relaxed = true)
    private lateinit var movieRepository: MovieRepository

    @Before
    fun setup() {
        movieRepository = MovieRepositoryImpl(movieRemoteDataSource, programLocalDataSource)
    }

    @Test
    fun `given success when getPopularMovies is called then should save locally and return flow`() {
        val expected = listOf(Program(1, "", "", ProgramType.Movie.Popular))
        every { movieRemoteDataSource.getPopularMovies() } answers { flowOf(expected) }

        val response = runBlocking {
            movieRepository.getPopularMovies().first()
        }

        Assert.assertEquals(expected, response)
        coVerify(exactly = 1) {
            movieRemoteDataSource.getPopularMovies()
            programLocalDataSource.savePrograms(expected)
            programLocalDataSource.deleteProgramsByType(ProgramType.Movie.Popular)
        }
        coVerify(exactly = 0) {
            programLocalDataSource.getProgramsByType(any())
        }
    }

    @Test
    fun `given exception when getPopularMovies is called then should get local data and emit it`() {
        val expected = mockk<List<Program>>()
        every { movieRemoteDataSource.getPopularMovies() } answers { flow { throw Exception() } }
        coEvery { programLocalDataSource.getProgramsByType(ProgramType.Movie.Popular) } answers { expected }

        val response = runBlocking {
            movieRepository.getPopularMovies().first()
        }

        Assert.assertEquals(expected, response)
        coVerify(exactly = 1) {
            movieRemoteDataSource.getPopularMovies()
            programLocalDataSource.getProgramsByType(ProgramType.Movie.Popular)
        }
        coVerify(exactly = 0) {
            programLocalDataSource.savePrograms(any())
            programLocalDataSource.deleteProgramsByType(any())
        }
    }

    @Test
    fun `given success when getTopRatedMovies is called then should save locally and return flow`() {
        val expected = listOf(Program(1, "", "", ProgramType.Movie.TopRated))
        every { movieRemoteDataSource.getTopRatedMovies() } answers { flowOf(expected) }

        val response = runBlocking {
            movieRepository.getTopRatedMovies().first()
        }

        Assert.assertEquals(expected, response)
        coVerify(exactly = 1) {
            movieRemoteDataSource.getTopRatedMovies()
            programLocalDataSource.savePrograms(expected)
            programLocalDataSource.deleteProgramsByType(ProgramType.Movie.TopRated)
        }
        coVerify(exactly = 0) {
            programLocalDataSource.getProgramsByType(any())
        }
    }

    @Test
    fun `given exception when getTopRatedMovies is called then should get local data and emit it`() {
        val expected = mockk<List<Program>>()
        every { movieRemoteDataSource.getTopRatedMovies() } answers { flow { throw Exception() } }
        coEvery { programLocalDataSource.getProgramsByType(ProgramType.Movie.TopRated) } answers { expected }

        val response = runBlocking {
            movieRepository.getTopRatedMovies().first()
        }

        Assert.assertEquals(expected, response)
        coVerify(exactly = 1) {
            movieRemoteDataSource.getTopRatedMovies()
            programLocalDataSource.getProgramsByType(ProgramType.Movie.TopRated)
        }
        coVerify(exactly = 0) {
            programLocalDataSource.savePrograms(any())
            programLocalDataSource.deleteProgramsByType(any())
        }
    }
}
