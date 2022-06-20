package com.bsav.home.domain.usecase

import com.bsav.home.domain.model.Program
import com.bsav.home.domain.model.ProgramType
import com.bsav.home.domain.model.exception.ProgramTypeNotFoundException
import com.bsav.home.domain.usecase.strategy.GetPopularMoviesStrategy
import com.bsav.home.domain.usecase.strategy.GetTopRatedTvShowsStrategy
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.Flow
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetProgramsByTypeTest {

    private val getPopularMoviesStrategy = mockk<GetPopularMoviesStrategy>()
    private val getTopRatedTvShowsStrategy = mockk<GetTopRatedTvShowsStrategy>()
    private val strategies = mapOf(
        ProgramType.Movie.Popular::class.java to getPopularMoviesStrategy,
        ProgramType.TvShow.TopRated::class.java to getTopRatedTvShowsStrategy
    )
    private lateinit var getProgramsByType: GetProgramsByType

    @Before
    fun setup() {
        getProgramsByType = GetProgramsByType(strategies)
    }

    @Test
    fun `given strategy in map when invoke is called then should call execute and return its value`() {
        val expected = mockk<Flow<List<Program>>>()
        every { getPopularMoviesStrategy.execute() } answers { expected }

        val response = getProgramsByType(ProgramType.Movie.Popular)

        verify(exactly = 1) { getPopularMoviesStrategy.execute() }
        Assert.assertEquals(expected, response)
    }

    @Test(expected = ProgramTypeNotFoundException::class)
    fun `given program type no exist in routes when resolveDestination is called then should throw exception`() {
        verify(exactly = 0) { getPopularMoviesStrategy.execute() }

        getProgramsByType(ProgramType.Movie.TopRated)
    }
}
