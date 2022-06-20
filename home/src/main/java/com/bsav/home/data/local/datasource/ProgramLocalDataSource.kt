package com.bsav.home.data.local.datasource

import com.bsav.home.domain.model.Program
import com.bsav.home.domain.model.ProgramType

interface ProgramLocalDataSource {
    fun getProgramsByType(type: ProgramType): List<Program>
    suspend fun savePrograms(programs: List<Program>)
    suspend fun deleteProgramsByType(type: ProgramType)
}
