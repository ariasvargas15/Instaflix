package com.bsav.movie.data.remote.datasource

import com.bsav.movie.data.remote.model.MovieNetwork
import com.bsav.movie.data.remote.service.MovieService
import com.bsav.movie.domain.model.Movie
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
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
    fun `given an id when getMovieById is called then should call service and emit flow`() {
        val movieNetwork = mockk<MovieNetwork>()
        val movie = mockk<Movie>()
        coEvery { movieService.getMovieById(1) } answers { movieNetwork }
        every { movieNetwork.mapToDomain() } answers { movie }

        val response = runBlocking {
            movieRemoteDataSource.getMovieById(1).first()
        }

        coVerify(exactly = 1) {
            movieService.getMovieById(1)
        }
        Assert.assertEquals(movie, response)
    }
}
