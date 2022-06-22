package com.bsav.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val posterPath: String,
    val adult: Boolean,
    val backdropPath: String,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
)
