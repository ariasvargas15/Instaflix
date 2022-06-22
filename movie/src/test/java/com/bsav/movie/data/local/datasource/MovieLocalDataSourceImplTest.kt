package com.bsav.movie.data.local.datasource

import com.bsav.db.dao.MovieDao
import com.bsav.db.entity.MovieEntity
import com.bsav.movie.data.local.mapFromDomainToEntity
import com.bsav.movie.data.local.mapFromEntityToDomain
import com.bsav.movie.domain.model.Movie
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

class MovieLocalDataSourceImplTest {

    private val movieDao = mockk<MovieDao>()
    private lateinit var movieLocalDataSource: MovieLocalDataSource

    @Before
    fun setup() {
        movieLocalDataSource = MovieLocalDataSourceImpl(movieDao)
    }

    @Test
    fun `when getMovieById is called then should call dao`() {
        val entity = mockk<MovieEntity>()
        val expected = mockk<Movie>()
        mockkStatic(MovieEntity::mapFromEntityToDomain)
        coEvery { movieDao.getMovieById(1) } answers { entity }
        every { entity.mapFromEntityToDomain() } answers { expected }

        val response = runBlocking {
            movieLocalDataSource.getMovieById(1)
        }

        coVerify(exactly = 1) {
            movieDao.getMovieById(1)
        }
        Assert.assertEquals(expected, response)
    }

    @Test
    fun `when saveMovie is called then should call dao`() {
        val entity = mockk<MovieEntity>()
        val movie = mockk<Movie>()
        mockkStatic(Movie::mapFromDomainToEntity)
        every { movie.mapFromDomainToEntity() } answers { entity }
        coEvery { movieDao.saveMovie(entity) } just runs

        runBlocking {
            movieLocalDataSource.saveMovie(movie)
        }

        coVerify(exactly = 1) {
            movieDao.saveMovie(entity)
        }
    }
}
