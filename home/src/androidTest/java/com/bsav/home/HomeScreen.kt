package com.bsav.home

import com.bsav.home.presentation.HomeFragment
import com.bsav.testutils.Page
import com.bsav.testutils.isTextDisplayed
import com.bsav.testutils.isViewDisplayed
import com.bsav.testutils.launchFragmentInHiltContainer
import com.bsav.testutils.scrollListToPosition
import com.bsav.testutils.scrollUp
import com.bsav.testutils.verifyItemsCountInRecyclerView

class HomeScreen : Page() {

    fun launchFragment() = apply {
        launchFragmentInHiltContainer<HomeFragment>()
    }

    fun performScrollUp() = apply {
        scrollUp()
    }

    fun checkInstaflixImageIsDisplayed() = apply {
        isViewDisplayed(R.id.instaflix_logo)
    }

    fun isPopularMoviesTitleDisplayed() = apply {
        isTextDisplayed("Popular movies")
    }

    fun verifyExistSixPopularMovies() = apply {
        verifyItemsCountInRecyclerView(R.id.recycler_popular_movies, 6)
    }

    fun isFirstPopularMovieDisplayed() = apply {
        isTextDisplayed("Fantastic Beasts: The Secrets of Dumbledore")
    }

    fun scrollToLastPopularMovie() = apply {
        scrollListToPosition(R.id.recycler_popular_movies, 5)
    }

    fun isLastPopularMovieDisplayed() = apply {
        isTextDisplayed("Doctor Strange in the Multiverse of Madness")
    }

    fun isTopRatedMoviesTitleDisplayed() = apply {
        isTextDisplayed("Top rated movies")
    }

    fun verifyExistSixTopRatedMovies() = apply {
        verifyItemsCountInRecyclerView(R.id.recycler_top_rated_movies, 6)
    }

    fun isFirstTopRatedMovieDisplayed() = apply {
        isTextDisplayed("The Shawshank Redemption")
    }

    fun scrollToLastTopRatedMovie() = apply {
        scrollListToPosition(R.id.recycler_top_rated_movies, 5)
    }

    fun isLastTopRatedMovieDisplayed() = apply {
        isTextDisplayed("Dou kyu sei – Classmates")
    }

    fun isPopularTvShowTitleDisplayed() = apply {
        isTextDisplayed("Popular Tv Shows")
    }

    fun verifyExistSixPopularTvShows() = apply {
        verifyItemsCountInRecyclerView(R.id.recycler_popular_tv_shows, 6)
    }

    fun isFirstPopularTvShowDisplayed() = apply {
        isTextDisplayed("The Boys")
    }

    fun scrollToLastPopularTvShow() = apply {
        scrollListToPosition(R.id.recycler_popular_tv_shows, 5)
    }

    fun isLastPopularTvShowDisplayed() = apply {
        isTextDisplayed("Peaky Blinders")
    }

    fun isTopRatedTvShowTitleDisplayed() = apply {
        isTextDisplayed("Top rated Tv Shows")
    }

    fun verifyExistSixTopRatedTvShows() = apply {
        verifyItemsCountInRecyclerView(R.id.recycler_top_rated_tv_shows, 6)
    }

    fun isFirstTopRatedTvShowDisplayed() = apply {
        isTextDisplayed("The D'Amelio Show")
    }

    fun scrollToLastTopRatedTvShow() = apply {
        scrollListToPosition(R.id.recycler_top_rated_tv_shows, 5)
    }

    fun isLastTopRatedTvShowDisplayed() = apply {
        isTextDisplayed("Given")
    }
}
