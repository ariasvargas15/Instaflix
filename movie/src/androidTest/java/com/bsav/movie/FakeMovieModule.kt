package com.bsav.movie

import com.bsav.movie.data.remote.di.MovieServiceModule
import com.bsav.movie.data.remote.model.MovieNetwork
import com.bsav.movie.data.remote.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.coEvery
import io.mockk.mockk

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [MovieServiceModule::class]
)
object FakeMovieModule {

    @Provides
    @Reusable
    fun provideServiceModule(): MovieService = mockk()
}

fun MovieService.stubMovie() {
    coEvery {
        getMovieById(1)
    } answers {
        MovieNetwork(
            1,
            "Lightyear",
            "/vpILbP9eOQEtdQgl4vgjZUNY07r.jpg",
            false,
            "/7JytVNKaAQOFR2dFVpIIUsdwS15.jpg",
            "Legendary Space Ranger Buzz Lightyear embarks on an intergalactic adventure alongside a group of ambitious recruits and his robot companion Sox.",
            "2022-06-01",
            7.0
        )
    }
}

fun MovieService.stubMovieAdult() {
    coEvery {
        getMovieById(1)
    } answers {
        MovieNetwork(
            1,
            "Lightyear",
            "/vpILbP9eOQEtdQgl4vgjZUNY07r.jpg",
            true,
            "/7JytVNKaAQOFR2dFVpIIUsdwS15.jpg",
            "Legendary Space Ranger Buzz Lightyear embarks on an intergalactic adventure alongside a group of ambitious recruits and his robot companion Sox.",
            "2022-06-01",
            7.0
        )
    }
}
