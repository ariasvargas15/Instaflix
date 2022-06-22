package com.bsav.tvshow

import com.bsav.tvshow.data.remote.di.TvShowServiceModule
import com.bsav.tvshow.data.remote.model.TvShowNetwork
import com.bsav.tvshow.data.remote.service.TvShowService
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
    replaces = [TvShowServiceModule::class]
)
object FakeTvShowModule {

    @Provides
    @Reusable
    fun provideServiceModule(): TvShowService = mockk()
}

fun TvShowService.stubTvShow() {
    coEvery {
        getTvShowById(1)
    } answers {
        TvShowNetwork(
            1,
            "Stranger Things",
            "/vpILbP9eOQEtdQgl4vgjZUNY07r.jpg",
            false,
            "/7JytVNKaAQOFR2dFVpIIUsdwS15.jpg",
            "Stranger Things description",
            "2022-06-01",
            7.0,
            6,
            6
        )
    }
}

fun TvShowService.stubTvShowAdult() {
    coEvery {
        getTvShowById(1)
    } answers {
        TvShowNetwork(
            1,
            "Stranger Things",
            "/vpILbP9eOQEtdQgl4vgjZUNY07r.jpg",
            true,
            "/7JytVNKaAQOFR2dFVpIIUsdwS15.jpg",
            "Stranger Things description",
            "2022-06-01",
            7.0,
            6,
            6
        )
    }
}
