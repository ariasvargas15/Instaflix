package com.bsav.home.domain.usecase.strategy

import com.bsav.home.domain.model.Program
import kotlinx.coroutines.flow.Flow

interface GetProgramsStrategy {
    fun execute(): Flow<List<Program>>
}