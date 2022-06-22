package com.bsav.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bsav.db.entity.TvShowEntity

@Dao
interface TvShowDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTvShow(tvShow: TvShowEntity)

    @Query("SELECT * FROM TvShowEntity WHERE id = :id")
    fun getTvShowById(id: Int): TvShowEntity
}
