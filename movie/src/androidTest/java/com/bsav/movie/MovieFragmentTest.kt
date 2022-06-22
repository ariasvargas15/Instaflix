package com.bsav.movie

import com.bsav.movie.data.remote.service.MovieService
import com.bsav.testutils.BaseAndroidTest
import com.bsav.testutils.Page.Companion.on
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class MovieFragmentTest : BaseAndroidTest() {

    @Inject
    lateinit var movieService: MovieService

    @Test
    fun openMovieDetail() {
        movieService.stubMovie()
        on<MovieScreen>()
            .launchFragment()
            .isMovieLabelDisplayed()
            .isTitleDisplayed()
            .isDescriptionDisplayed()
            .isReleaseYearDisplayed()
            .isScoreDisplayed()
            .isPlus18NotDisplayed()
    }

    @Test
    fun openMovieDetailAdult() {
        movieService.stubMovieAdult()
        on<MovieScreen>()
            .launchFragment()
            .isMovieLabelDisplayed()
            .isTitleDisplayed()
            .isDescriptionDisplayed()
            .isReleaseYearDisplayed()
            .isScoreDisplayed()
            .isPlus18Displayed()
    }
}
