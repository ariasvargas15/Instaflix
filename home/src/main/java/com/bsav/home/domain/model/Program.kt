package com.bsav.home.domain.model

data class Program(
    val id: Int,
    val name: String,
    val posterPath: String?,
    val type: ProgramType,
)
