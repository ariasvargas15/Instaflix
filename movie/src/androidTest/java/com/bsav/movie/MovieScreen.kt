package com.bsav.movie

import android.os.Bundle
import com.bsav.movie.presentation.MovieFragment
import com.bsav.testutils.Page
import com.bsav.testutils.isTextDisplayed
import com.bsav.testutils.isTextNotDisplayed
import com.bsav.testutils.launchFragmentInHiltContainer

class MovieScreen : Page() {

    fun launchFragment() = apply {
        launchFragmentInHiltContainer<MovieFragment>(
            Bundle().apply {
                putInt("programId", 1)
            }
        )
    }

    fun isMovieLabelDisplayed() = apply {
        isTextDisplayed("Movie")
    }

    fun isTitleDisplayed() = apply {
        isTextDisplayed("Lightyear")
    }

    fun isDescriptionDisplayed() = apply {
        isTextDisplayed("Legendary Space Ranger Buzz Lightyear embarks on an intergalactic adventure alongside a group of ambitious recruits and his robot companion Sox.")
    }

    fun isReleaseYearDisplayed() = apply {
        isTextDisplayed("2022")
    }

    fun isScoreDisplayed() = apply {
        isTextDisplayed("7.0\u2605")
    }

    fun isPlus18NotDisplayed() = apply {
        isTextNotDisplayed("+18")
    }

    fun isPlus18Displayed() = apply {
        isTextDisplayed("+18")
    }
}
