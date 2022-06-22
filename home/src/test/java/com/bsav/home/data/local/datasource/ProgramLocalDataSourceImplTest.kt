package com.bsav.home.data.local.datasource

import com.bsav.db.dao.ProgramDao
import com.bsav.db.entity.ProgramEntity
import com.bsav.home.data.local.mapFromDomainToEntity
import com.bsav.home.data.local.mapFromEntityToDomain
import com.bsav.home.data.local.mapToString
import com.bsav.home.domain.model.Program
import com.bsav.home.domain.model.ProgramType
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.runs
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class ProgramLocalDataSourceImplTest {

    private val programDao = mockk<ProgramDao>()
    private lateinit var programLocalDataSource: ProgramLocalDataSource

    @Before
    fun setup() {
        programLocalDataSource = ProgramLocalDataSourceImpl(programDao)
    }

    @Test
    fun `when getProgramsByType should call dao`() {
        val type = ProgramType.Movie.Popular.mapToString()
        val programs = listOf<ProgramEntity>(mockk())
        val expected = listOf<Program>(mockk())
        coEvery { programDao.getProgramsByType(type) } answers { programs }
        mockkStatic(ProgramEntity::mapFromEntityToDomain)
        every { programs[0].mapFromEntityToDomain() } answers { expected[0] }

        val response = runBlocking {
            programLocalDataSource.getProgramsByType(ProgramType.Movie.Popular)
        }
        Assert.assertEquals(expected, response)
        coVerify(exactly = 1) {
            programDao.getProgramsByType(type)
        }
    }

    @Test
    fun `when savePrograms should call dao`() {
        val entities = listOf<ProgramEntity>(mockk())
        val programs = listOf<Program>(mockk())
        mockkStatic(Program::mapFromDomainToEntity)
        every { programs[0].mapFromDomainToEntity() } answers { entities[0] }
        coEvery { programDao.saveProgramList(entities) } just runs

        runBlocking {
            programLocalDataSource.savePrograms(programs)
        }
        coVerify(exactly = 1) {
            programDao.saveProgramList(entities)
        }
    }

    @Test
    fun `when deleteProgramsByType should call dao`() {
        val type = ProgramType.Movie.Popular.mapToString()
        coEvery { programDao.deleteProgramsByType(type) } just runs

        runBlocking {
            programLocalDataSource.deleteProgramsByType(ProgramType.Movie.Popular)
        }
        coVerify(exactly = 1) {
            programDao.deleteProgramsByType(type)
        }
    }
}
