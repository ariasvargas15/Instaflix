package com.bsav.home.data.remote.di

import com.bsav.home.data.remote.service.MovieService
import com.bsav.home.data.remote.service.TvShowService
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    @Reusable
    fun provideMovieService(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)

    @Provides
    @Reusable
    fun provideTvShowService(retrofit: Retrofit): TvShowService = retrofit.create(TvShowService::class.java)
}