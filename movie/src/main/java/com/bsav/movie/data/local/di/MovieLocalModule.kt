package com.bsav.movie.data.local.di

import com.bsav.movie.data.local.datasource.MovieLocalDataSource
import com.bsav.movie.data.local.datasource.MovieLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object MovieLocalModule {

    @Provides
    @Reusable
    fun provideMovieLocalDataSource(movieLocalDataSourceImpl: MovieLocalDataSourceImpl): MovieLocalDataSource = movieLocalDataSourceImpl
}
