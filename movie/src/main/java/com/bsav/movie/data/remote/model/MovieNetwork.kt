package com.bsav.movie.data.remote.model

import com.bsav.movie.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieNetwork(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Double,
) {
    fun mapToDomain(): Movie = Movie(
        id,
        title,
        posterPath,
        adult,
        backdropPath,
        overview,
        releaseDate,
        voteAverage,
    )
}
