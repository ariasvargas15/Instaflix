package com.bsav.home.data.remote.datasource

import com.bsav.home.data.remote.model.MovieNetwork
import com.bsav.home.data.remote.model.PageMovieNetwork
import com.bsav.home.data.remote.service.MovieService
import com.bsav.home.domain.model.Program
import com.bsav.home.domain.model.ProgramType
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class MovieRemoteDataSourceImplTest {

    private val movieService = mockk<MovieService>()
    private lateinit var movieRemoteDataSource: MovieRemoteDataSource

    @Before
    fun setup() {
        movieRemoteDataSource = MovieRemoteDataSourceImpl(movieService)
    }

    @Test
    fun `when getPopularMovies is called then should call service and emit the result`() {
        val service = PageMovieNetwork(
            listOf(MovieNetwork(1, "path", "title"))
        )
        coEvery { movieService.getPopularMovies() } answers { service }
        val expected = listOf(Program(1, "title", "path", ProgramType.Movie.Popular))

        val response = runBlocking {
            movieRemoteDataSource.getPopularMovies().first()
        }
        Assert.assertEquals(expected, response)
    }

    @Test
    fun `when getTopRatedMovies is called then should call service and emit the result`() {
        val service = PageMovieNetwork(
            listOf(MovieNetwork(1, "path", "title"))
        )
        coEvery { movieService.getTopRatedMovies() } answers { service }
        val expected = listOf(Program(1, "title", "path", ProgramType.Movie.TopRated))

        val response = runBlocking {
            movieRemoteDataSource.getTopRatedMovies().first()
        }
        Assert.assertEquals(expected, response)
    }
}
