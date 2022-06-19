package com.bsav.home.domain.usecase.strategy

import com.bsav.home.domain.model.ProgramType
import com.bsav.home.domain.usecase.strategy.di.ProgramTypeKey
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoMap

@Module
@InstallIn(SingletonComponent::class)
object GetProgramsStrategyModule {

    @IntoMap
    @Provides
    @Reusable
    @ProgramTypeKey(ProgramType.Movie.Popular::class)
    fun provideGetPopularMoviesStrategy(getPopularMoviesStrategy: GetPopularMoviesStrategy): GetProgramsStrategy = getPopularMoviesStrategy

    @IntoMap
    @Provides
    @Reusable
    @ProgramTypeKey(ProgramType.Movie.TopRated::class)
    fun provideGetTopRatedMoviesStrategy(getTopRatedMoviesStrategy: GetTopRatedMoviesStrategy): GetProgramsStrategy = getTopRatedMoviesStrategy

    @IntoMap
    @Provides
    @Reusable
    @ProgramTypeKey(ProgramType.TvShow.Popular::class)
    fun provideGetPopularTvShowsStrategy(getPopularTvShowsStrategy: GetPopularTvShowsStrategy): GetProgramsStrategy = getPopularTvShowsStrategy

    @IntoMap
    @Provides
    @Reusable
    @ProgramTypeKey(ProgramType.TvShow.TopRated::class)
    fun provideGetTopRatedTvShowsStrategy(getTopRatedTvShowsStrategy: GetTopRatedTvShowsStrategy): GetProgramsStrategy = getTopRatedTvShowsStrategy

}