package com.bsav.home.domain.usecase.strategy

import com.bsav.home.domain.MovieRepository
import com.bsav.home.domain.model.Program
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetTopRatedMoviesStrategyTest {

    private val movieRepository = mockk<MovieRepository>()
    private lateinit var getProgramsStrategy: GetProgramsStrategy

    @Before
    fun setup() {
        getProgramsStrategy = GetTopRatedMoviesStrategy(movieRepository)
    }

    @Test
    fun `when execute is called then should call getTopRatedMovies and return its value`() {
        val flowPrograms = flowOf(mockk<List<Program>>())
        every { movieRepository.getTopRatedMovies() } answers { flowPrograms }

        val response = getProgramsStrategy.execute()

        verify(exactly = 1) { movieRepository.getTopRatedMovies() }
        Assert.assertEquals(flowPrograms, response)
    }
}
