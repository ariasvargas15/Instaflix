package com.bsav.tvshow.data.remote.di

import com.bsav.tvshow.data.remote.datasource.TvShowRemoteDataSource
import com.bsav.tvshow.data.remote.datasource.TvShowRemoteDataSourceImpl
import com.bsav.tvshow.data.remote.service.TvShowService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object TvShowNetworkModule {

    @Provides
    @Reusable
    fun provideTvShowRemoteDataSource(tvShowRemoteDataSourceImpl: TvShowRemoteDataSourceImpl): TvShowRemoteDataSource = tvShowRemoteDataSourceImpl
}

@Module
@InstallIn(SingletonComponent::class)
object TvShowServiceModule {

    @Provides
    @Reusable
    fun provideTvShowService(retrofit: Retrofit): TvShowService = retrofit.create(TvShowService::class.java)
}
