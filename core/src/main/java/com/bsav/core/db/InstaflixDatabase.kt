package com.bsav.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bsav.core.db.dao.MovieDao
import com.bsav.core.db.dao.ProgramDao
import com.bsav.core.db.dao.TvShowDao
import com.bsav.core.db.entity.MovieEntity
import com.bsav.core.db.entity.ProgramEntity
import com.bsav.core.db.entity.TvShowEntity

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
