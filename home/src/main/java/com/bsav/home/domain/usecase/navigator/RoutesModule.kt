package com.bsav.home.domain.usecase.navigator

import com.bsav.home.domain.model.ProgramType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

private val routes = mapOf(
    ProgramType.Movie.Popular to "movie_detail",
    ProgramType.Movie.TopRated to "movie_detail",
    ProgramType.TvShow.Popular to "tv_show_detail",
    ProgramType.TvShow.TopRated to "tv_show_detail",
)

@Module
@InstallIn(SingletonComponent::class)
object RoutesModule {
    @Provides
    @Singleton
    fun provideRoutes(): Map<ProgramType, String> = routes
}
