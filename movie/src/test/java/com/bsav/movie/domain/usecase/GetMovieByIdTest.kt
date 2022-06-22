package com.bsav.movie.domain.usecase

import com.bsav.movie.domain.MovieRepository
import com.bsav.movie.domain.model.Movie
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetMovieByIdTest {

    private val movieRepository = mockk<MovieRepository>()
    private lateinit var getMovieById: GetMovieById

    @Before
    fun setup() {
        getMovieById = GetMovieById(movieRepository)
    }

    @Test
    fun `when invoke is called then should call repository`() {
        val expected = flowOf(mockk<Movie>())
        every { movieRepository.getMovieById(1) } answers { expected }

        val response = getMovieById(1)

        Assert.assertEquals(expected, response)
    }
}
