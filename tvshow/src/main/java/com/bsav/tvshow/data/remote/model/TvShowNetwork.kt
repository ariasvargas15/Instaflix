package com.bsav.tvshow.data.remote.model

import com.bsav.tvshow.domain.model.TvShow
import com.google.gson.annotations.SerializedName

data class TvShowNetwork(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val title: String,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("overview") val overview: String,
    @SerializedName("last_air_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("number_of_seasons") val seasons: Int,
    @SerializedName("number_of_episodes") val episodes: Int,
) {
    fun mapToDomain(): TvShow = TvShow(
        id,
        title,
        posterPath,
        adult,
        backdropPath,
        overview,
        releaseDate,
        voteAverage,
        seasons,
        episodes
    )
}
