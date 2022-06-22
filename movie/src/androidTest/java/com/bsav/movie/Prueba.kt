package com.bsav.movie

import android.os.Bundle
import com.bsav.movie.data.remote.di.MovieServiceModule
import com.bsav.movie.data.remote.model.MovieNetwork
import com.bsav.movie.data.remote.service.MovieService
import com.bsav.movie.presentation.MovieFragment
import com.bsav.testutils.launchFragmentInHiltContainer
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
class MovieFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var movieService: MovieService

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun checkMovie() {
        coEvery { movieService.getMovieById(1) } answers {
            MovieNetwork(
                1,
                "Titulo",
                "path",
                true,
                "path",
                "Description",
                "2020-1-1",
                2.3
            )
        }
        launchFragmentInHiltContainer<MovieFragment>(Bundle().apply { putInt("programId", 1) })
        Thread.sleep(10000)
    }
}

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [MovieServiceModule::class]
)
object FakeMovieServiceModule {

    @Provides
    @Reusable
    fun provideServiceModule(): MovieService = mockk()
}
