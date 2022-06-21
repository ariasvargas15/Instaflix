package com.bsav.core.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bsav.core.db.entity.MovieEntity

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovie(movie: MovieEntity)

    @Query("SELECT * FROM MovieEntity WHERE id = :id")
    fun getMovieById(id: Int): MovieEntity
}
