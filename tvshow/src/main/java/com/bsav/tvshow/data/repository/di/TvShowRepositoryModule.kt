package com.bsav.tvshow.data.repository.di

import com.bsav.tvshow.data.repository.TvShowRepositoryImpl
import com.bsav.tvshow.domain.TvShowRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TvShowRepositoryModule {

    @Provides
    @Reusable
    fun provideTvShowRepository(tvShowRepositoryImpl: TvShowRepositoryImpl): TvShowRepository = tvShowRepositoryImpl
}
