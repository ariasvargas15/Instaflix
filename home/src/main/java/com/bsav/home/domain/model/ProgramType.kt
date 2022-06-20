package com.bsav.home.domain.model

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
