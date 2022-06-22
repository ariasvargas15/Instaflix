package com.bsav.tvshow.data.local.di

import com.bsav.tvshow.data.local.datasource.TvShowLocalDataSource
import com.bsav.tvshow.data.local.datasource.TvShowLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object TvShowLocalModule {

    @Provides
    @Reusable
    fun provideTvShowLocalDataSource(tvShowLocalDataSourceImpl: TvShowLocalDataSourceImpl): TvShowLocalDataSource = tvShowLocalDataSourceImpl
}
