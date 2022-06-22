package com.bsav.home

import com.bsav.home.data.remote.service.MovieService
import com.bsav.home.data.remote.service.TvShowService
import com.bsav.testutils.BaseAndroidTest
import com.bsav.testutils.Page.Companion.on
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class HomeFragmentTest : BaseAndroidTest() {

    @Inject
    lateinit var tvShowService: TvShowService

    @Inject
    lateinit var movieService: MovieService

    @Before
    override fun setup() {
        super.setup()
        movieService.stubPopularMovies()
        movieService.stubTopRatedMovies()
        tvShowService.stubPopularTvShows()
        tvShowService.stubTopRatedTvShows()
    }

    @Test
    fun verifyPopularMovies() {
        on<HomeScreen>()
            .launchFragment()
            .checkInstaflixImageIsDisplayed()
            .isPopularMoviesTitleDisplayed()
            .verifyExistSixPopularMovies()
            .isFirstPopularMovieDisplayed()
            .scrollToLastPopularMovie()
            .isLastPopularMovieDisplayed()
    }

    @Test
    fun verifyTopRatedMovies() {
        on<HomeScreen>()
            .launchFragment()
            .checkInstaflixImageIsDisplayed()
            .isTopRatedMoviesTitleDisplayed()
            .verifyExistSixTopRatedMovies()
            .isFirstTopRatedMovieDisplayed()
            .scrollToLastTopRatedMovie()
            .isLastTopRatedMovieDisplayed()
    }

    @Test
    fun verifyPopularTvShows() {
        on<HomeScreen>()
            .launchFragment()
            .checkInstaflixImageIsDisplayed()
            .performScrollUp()
            .isPopularTvShowTitleDisplayed()
            .verifyExistSixPopularTvShows()
            .isFirstPopularTvShowDisplayed()
            .scrollToLastPopularTvShow()
            .isLastPopularTvShowDisplayed()
    }

    @Test
    fun verifyTopRatedTvShows() {
        on<HomeScreen>()
            .launchFragment()
            .checkInstaflixImageIsDisplayed()
            .performScrollUp()
            .isTopRatedTvShowTitleDisplayed()
            .verifyExistSixTopRatedTvShows()
            .isFirstTopRatedTvShowDisplayed()
            .scrollToLastTopRatedTvShow()
            .isLastTopRatedTvShowDisplayed()
    }
}
