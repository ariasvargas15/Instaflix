package com.bsav.core.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TvShowEntity(
    val backdropPath: String? = null,
    val firstAirDate: String? = null,
    @PrimaryKey val id: Int,
    val name: String,
    val originalLanguage: String? = null,
    val originalName: String? = null,
    val overview: String? = null,
    val popularity: Double? = null,
    val posterPath: String? = null,
    val voteAverage: Double? = null,
    val voteCount: Int? = null
)
