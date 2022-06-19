package com.bsav.home.data.remote.di

import com.bsav.home.data.remote.datasource.MovieRemoteDataSource
import com.bsav.home.data.remote.datasource.MovieRemoteDataSourceImpl
import com.bsav.home.data.remote.datasource.TvShowRemoteDataSource
import com.bsav.home.data.remote.datasource.TvShowRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Provides
    @Reusable
    fun provideMovieRemoteDataSource(movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl): MovieRemoteDataSource = movieRemoteDataSourceImpl

    @Provides
    @Reusable
    fun provideTvShowRemoteDataSource(tvShowRemoteDataSourceImpl: TvShowRemoteDataSourceImpl): TvShowRemoteDataSource = tvShowRemoteDataSourceImpl
}
