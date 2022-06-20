package com.bsav.core.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProgramEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val posterPath: String,
    val type: String,
)
