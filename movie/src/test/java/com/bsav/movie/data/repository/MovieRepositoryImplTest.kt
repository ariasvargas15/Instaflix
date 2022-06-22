package com.bsav.movie.data.repository

import com.bsav.movie.data.local.datasource.MovieLocalDataSource
import com.bsav.movie.data.remote.datasource.MovieRemoteDataSource
import com.bsav.movie.domain.MovieRepository
import com.bsav.movie.domain.model.Movie
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

    private val movieLocalDataSource = mockk<MovieLocalDataSource>(relaxed = true)
    private val movieRemoteDataSource = mockk<MovieRemoteDataSource>()
    private lateinit var movieRepository: MovieRepository

    @Before
    fun setup() {
        movieRepository = MovieRepositoryImpl(movieLocalDataSource, movieRemoteDataSource)
    }

    @Test
    fun `given success when getMovieById is called then should save locally and return flow`() {
        val expected = mockk<Movie>()
        every { movieRemoteDataSource.getMovieById(1) } answers { flowOf(expected) }

        val response = runBlocking {
            movieRepository.getMovieById(1).first()
        }

        Assert.assertEquals(expected, response)
        coVerify(exactly = 1) {
            movieRemoteDataSource.getMovieById(1)
            movieLocalDataSource.saveMovie(expected)
        }
        coVerify(exactly = 0) {
            movieLocalDataSource.getMovieById(any())
        }
    }

    @Test
    fun `given exception when getMovieById is called then should get local data and emit it`() {
        val expected = mockk<Movie>()
        every { movieRemoteDataSource.getMovieById(1) } answers { flow { throw Exception() } }
        coEvery { movieLocalDataSource.getMovieById(1) } answers { expected }

        val response = runBlocking {
            movieRepository.getMovieById(1).first()
        }

        Assert.assertEquals(expected, response)
        coVerify(exactly = 1) {
            movieRemoteDataSource.getMovieById(1)
            movieLocalDataSource.getMovieById(1)
        }
        coVerify(exactly = 0) {
            movieLocalDataSource.saveMovie(any())
        }
    }
}
