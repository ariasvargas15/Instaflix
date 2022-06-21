package com.bsav.movie.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val posterPath: String,
    val adult: Boolean,
    val backdropPath: String?,
    val overview: String,
    val releaseDate: String,
    val voteAverage: Double,
)
