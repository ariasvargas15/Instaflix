package com.bsav.home.data.local.datasource

import com.bsav.core.db.dao.ProgramDao
import com.bsav.home.data.local.mapFromDomainToEntity
import com.bsav.home.data.local.mapFromEntityToDomain
import com.bsav.home.data.local.mapToString
import com.bsav.home.domain.model.Program
import com.bsav.home.domain.model.ProgramType
import javax.inject.Inject

class ProgramLocalDataSourceImpl @Inject constructor(
    private val programDao: ProgramDao
) : ProgramLocalDataSource {

    override suspend fun getProgramsByType(type: ProgramType): List<Program> =
        programDao
            .getProgramsByType(type.mapToString())
            .map { it.mapFromEntityToDomain() }

    override suspend fun savePrograms(programs: List<Program>) {
        programs.map {
            it.mapFromDomainToEntity()
        }.let {
            programDao.saveProgramList(it)
        }
    }

    override suspend fun deleteProgramsByType(type: ProgramType) {
        programDao.deleteProgramsByType(type.mapToString())
    }
}
