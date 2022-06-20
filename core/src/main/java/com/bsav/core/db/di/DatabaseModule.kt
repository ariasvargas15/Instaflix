package com.bsav.core.db.di

import android.content.Context
import androidx.room.Room
import com.bsav.core.db.InstaflixDatabase
import com.bsav.core.db.dao.MovieDao
import com.bsav.core.db.dao.ProgramDao
import com.bsav.core.db.dao.TvShowDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(@ApplicationContext context: Context): InstaflixDatabase {
        return Room.databaseBuilder(context, InstaflixDatabase::class.java, DATABASE_NAME).build()
    }

    @Provides
    fun providesProgramDao(database: InstaflixDatabase): ProgramDao = database.programDao()

    @Provides
    fun providesMovieDao(database: InstaflixDatabase): MovieDao = database.movieDao()

    @Provides
    fun providesTvShowDao(database: InstaflixDatabase): TvShowDao = database.tvShowDao()
}

private const val DATABASE_NAME = "instaflix-database"
