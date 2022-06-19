package com.bsav.home.domain.usecase

import com.bsav.home.domain.model.Program
import com.bsav.home.domain.model.ProgramType
import com.bsav.home.domain.model.ProgramTypeNotFoundException
import com.bsav.home.domain.usecase.strategy.GetProgramsStrategy
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetProgramsByType @Inject constructor(
    private val strategies: Map<Class<out ProgramType>, @JvmSuppressWildcards GetProgramsStrategy>
) {
    operator fun invoke(programType: ProgramType): Flow<List<Program>> {
        return strategies[programType::class.java]?.execute() ?: throw ProgramTypeNotFoundException()
    }
}