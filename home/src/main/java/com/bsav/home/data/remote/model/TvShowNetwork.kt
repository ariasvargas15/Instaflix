package com.bsav.home.data.remote.model

import com.bsav.home.domain.model.Program
import com.bsav.home.domain.model.ProgramType
import com.google.gson.annotations.SerializedName

data class PageTvShowNetwork(
    @SerializedName("results") val results: List<TvShowNetwork>,
) {
    fun mapToDomain(type: ProgramType): List<Program> = results.map { it.mapToDomain(type) }
}

data class TvShowNetwork(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("poster_path") val posterPath: String?,
) {
    fun mapToDomain(type: ProgramType): Program = Program(
        id,
        name,
        posterPath,
        type
    )
}
