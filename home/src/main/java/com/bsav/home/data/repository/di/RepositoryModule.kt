package com.bsav.home.data.repository.di

import com.bsav.home.data.repository.MovieRepositoryImpl
import com.bsav.home.data.repository.TvShowRepositoryImpl
import com.bsav.home.domain.MovieRepository
import com.bsav.home.domain.TvShowRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Reusable
    fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository = movieRepositoryImpl

    @Provides
    @Reusable
    fun provideTvShowRepository(tvShowRepositoryImpl: TvShowRepositoryImpl): TvShowRepository = tvShowRepositoryImpl
}
