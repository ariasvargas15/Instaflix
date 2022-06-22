package com.bsav.tvshow.domain.model

data class TvShow(
    val id: Int,
    val name: String,
    val posterPath: String,
    val adult: Boolean,
    val backdropPath: String?,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
    val seasons: Int,
    val episodes: Int
)
