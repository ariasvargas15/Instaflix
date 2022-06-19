package com.bsav.home.data.remote.model

import com.bsav.home.domain.model.Program
import com.bsav.home.domain.model.ProgramType
import com.google.gson.annotations.SerializedName

data class PageMovieNetwork(
    @SerializedName("results") val results: List<MovieNetwork>,
) {
    fun mapToDomain(type: ProgramType): List<Program> = results.map { it.mapToDomain(type) }
}

data class MovieNetwork(
    @SerializedName("id") val id: Int,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("title") val title: String,
) {
    fun mapToDomain(type: ProgramType) = Program(
        id,
        title,
        posterPath,
        type
    )
}