package com.bsav.tvshow.domain.usecase

import com.bsav.tvshow.domain.TvShowRepository
import com.bsav.tvshow.domain.model.TvShow
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class GetTvShowByIdTest {

    private val tvShowRepository = mockk<TvShowRepository>()
    private lateinit var getTvShowById: GetTvShowById

    @Before
    fun setup() {
        getTvShowById = GetTvShowById(tvShowRepository)
    }

    @Test
    fun `when invoke is called then should call repository`() {
        val expected = flowOf(mockk<TvShow>())
        every { tvShowRepository.getTvShowById(1) } answers { expected }

        val response = getTvShowById(1)

        Assert.assertEquals(expected, response)
    }
}
