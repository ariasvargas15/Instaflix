package com.bsav.movie.data.remote.di

import com.bsav.movie.data.remote.datasource.MovieRemoteDataSource
import com.bsav.movie.data.remote.datasource.MovieRemoteDataSourceImpl
import com.bsav.movie.data.remote.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object MovieNetworkModule {

    @Provides
    @Reusable
    fun provideMovieService(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)

    @Provides
    @Reusable
    fun provideMovieRemoteDataSource(movieRemoteDataSourceImpl: MovieRemoteDataSourceImpl): MovieRemoteDataSource = movieRemoteDataSourceImpl
}
