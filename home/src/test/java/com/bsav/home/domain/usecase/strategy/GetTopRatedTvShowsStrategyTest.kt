package com.bsav.home.domain.usecase.strategy

import com.bsav.home.domain.TvShowRepository
import com.bsav.home.domain.model.Program
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetTopRatedTvShowsStrategyTest {

    private val tvShowRepository = mockk<TvShowRepository>()
    private lateinit var getProgramsStrategy: GetProgramsStrategy

    @Before
    fun setup() {
        getProgramsStrategy = GetTopRatedTvShowsStrategy(tvShowRepository)
    }

    @Test
    fun `when execute is called then should call getTopRatedTvShows and return its value`() {
        val flowPrograms = flowOf(mockk<List<Program>>())
        every { tvShowRepository.getTopRatedTvShows() } answers { flowPrograms }

        val response = getProgramsStrategy.execute()

        verify(exactly = 1) { tvShowRepository.getTopRatedTvShows() }
        Assert.assertEquals(flowPrograms, response)
    }
}
