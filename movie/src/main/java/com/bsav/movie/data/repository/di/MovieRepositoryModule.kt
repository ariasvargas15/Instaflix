package com.bsav.movie.data.repository.di

import com.bsav.movie.data.repository.MovieRepositoryImpl
import com.bsav.movie.domain.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MovieRepositoryModule {

    @Provides
    @Reusable
    fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository = movieRepositoryImpl
}
