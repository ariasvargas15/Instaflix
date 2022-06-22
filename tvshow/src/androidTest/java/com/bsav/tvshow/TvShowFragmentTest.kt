package com.bsav.tvshow

import com.bsav.testutils.BaseAndroidTest
import com.bsav.testutils.Page.Companion.on
import com.bsav.tvshow.data.remote.service.TvShowService
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class TvShowFragmentTest : BaseAndroidTest() {

    @Inject
    lateinit var tvShowService: TvShowService

    @Test
    fun openMovieDetail() {
        tvShowService.stubTvShow()
        on<TvShowScreen>()
            .launchFragment()
            .isTvShowLabelDisplayed()
            .isTitleDisplayed()
            .isDescriptionDisplayed()
            .isReleaseYearDisplayed()
            .isScoreDisplayed()
            .isEpisodesDisplayed()
            .isSeasonsDisplayed()
            .isPlus18NotDisplayed()
    }

    @Test
    fun openMovieDetailAdult() {
        tvShowService.stubTvShowAdult()
        on<TvShowScreen>()
            .launchFragment()
            .isTvShowLabelDisplayed()
            .isTitleDisplayed()
            .isDescriptionDisplayed()
            .isReleaseYearDisplayed()
            .isScoreDisplayed()
            .isEpisodesDisplayed()
            .isSeasonsDisplayed()
            .isPlus18Displayed()
    }
}
