package com.bsav.home.domain.usecase.navigator

import com.bsav.home.domain.model.Destination
import com.bsav.home.domain.model.ProgramType
import com.bsav.home.domain.model.exception.ProgramTypeNotFoundException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ProgramNavigatorTest {

    private val getDeepLink = mockk<GetDeepLink>()
    private val routes = mapOf(
        ProgramType.Movie.Popular to "movie_popular_route",
        ProgramType.TvShow.TopRated to "tv_show_popular_route"
    )
    private lateinit var programNavigator: ProgramNavigator

    @Before
    fun setup() {
        programNavigator = ProgramNavigator(routes, getDeepLink)
    }

    @Test
    fun `given program type in routes when resolveDestination is called then should call getDeepLink`() {
        val params = mockk<Map<String, Any>>()
        val expected = mockk<Destination>()
        every { getDeepLink(params, "movie_popular_route") } answers { expected }

        val response = programNavigator.resolveDestination(params, ProgramType.Movie.Popular)

        verify(exactly = 1) { getDeepLink(params, "movie_popular_route") }
        Assert.assertEquals(expected, response)
    }

    @Test(expected = ProgramTypeNotFoundException::class)
    fun `given program type no exist in routes when resolveDestination is called then should throw exception`() {
        val params = mockk<Map<String, Any>>()

        verify(exactly = 0) { getDeepLink(any(), any()) }

        programNavigator.resolveDestination(params, ProgramType.Movie.TopRated)
    }
}
