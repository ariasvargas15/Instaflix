package com.bsav.home.domain.model

data class Program(
    val id: Int,
    val name: String,
    val posterPath: String?,
    val type: ProgramType,
)

sealed interface ProgramType {
    interface Movie : ProgramType {
        object Popular : ProgramType
        object TopRated : ProgramType
    }

    interface TvShow : ProgramType {
        object Popular : ProgramType
        object TopRated : ProgramType
    }
}
