package com.bsav.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bsav.db.dao.MovieDao
import com.bsav.db.dao.ProgramDao
import com.bsav.db.dao.TvShowDao
import com.bsav.db.entity.MovieEntity
import com.bsav.db.entity.ProgramEntity
import com.bsav.db.entity.TvShowEntity

@Database(
    entities = [
        ProgramEntity::class,
        MovieEntity::class,
        TvShowEntity::class
    ],
    version = 1, exportSchema = false
)
abstract class InstaflixDatabase : RoomDatabase() {
    abstract fun programDao(): ProgramDao
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
}
