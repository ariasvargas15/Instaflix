package com.bsav.tvshow

import android.os.Bundle
import com.bsav.testutils.Page
import com.bsav.testutils.isTextDisplayed
import com.bsav.testutils.isTextNotDisplayed
import com.bsav.testutils.launchFragmentInHiltContainer
import com.bsav.tvshow.presentation.TvShowFragment

class TvShowScreen : Page() {

    fun launchFragment() = apply {
        launchFragmentInHiltContainer<TvShowFragment>(
            Bundle().apply {
                putInt("programId", 1)
            }
        )
    }

    fun isTvShowLabelDisplayed() = apply {
        isTextDisplayed("Tv show")
    }

    fun isTitleDisplayed() = apply {
        isTextDisplayed("Stranger Things")
    }

    fun isDescriptionDisplayed() = apply {
        isTextDisplayed("Stranger Things description")
    }

    fun isReleaseYearDisplayed() = apply {
        isTextDisplayed("2022")
    }

    fun isScoreDisplayed() = apply {
        isTextDisplayed("7.0\u2605")
    }

    fun isEpisodesDisplayed() = apply {
        isTextDisplayed("Episodes: 6")
    }

    fun isSeasonsDisplayed() = apply {
        isTextDisplayed("Seasons: 6")
    }

    fun isPlus18NotDisplayed() = apply {
        isTextNotDisplayed("+18")
    }

    fun isPlus18Displayed() = apply {
        isTextDisplayed("+18")
    }
}
