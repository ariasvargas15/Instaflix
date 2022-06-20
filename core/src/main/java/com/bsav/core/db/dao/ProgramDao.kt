package com.bsav.core.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bsav.core.db.entity.ProgramEntity

@Dao
interface ProgramDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveProgramList(programList: List<ProgramEntity>)

    @Query("SELECT * FROM ProgramEntity WHERE type = :type")
    suspend fun getProgramsByType(type: String): List<ProgramEntity>

    @Query("DELETE FROM ProgramEntity WHERE type = :type")
    suspend fun deleteProgramsByType(type: String)
}
