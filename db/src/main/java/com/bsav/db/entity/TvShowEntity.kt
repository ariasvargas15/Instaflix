package com.bsav.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TvShowEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val posterPath: String,
    val adult: Boolean,
    val backdropPath: String,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
    val seasons: Int,
    val episodes: Int
)
