package com.bsav.home.data.repository

import com.bsav.home.data.local.datasource.ProgramLocalDataSource
import com.bsav.home.data.remote.datasource.MovieRemoteDataSource
import com.bsav.home.domain.MovieRepository
import com.bsav.home.domain.model.Program
import com.bsav.home.domain.model.ProgramType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieRemoteDataSource: MovieRemoteDataSource,
    private val programLocalDataSource: ProgramLocalDataSource
) : MovieRepository {

    override fun getPopularMovies(): Flow<List<Program>> =
        movieRemoteDataSource
            .getPopularMovies()
            .onEach { saveMoviesInCache(it) }
            .catch { emit(getCachedMoviesByType(ProgramType.Movie.Popular)) }

    override fun getTopRatedMovies(): Flow<List<Program>> =
        movieRemoteDataSource
            .getTopRatedMovies()
            .onEach { saveMoviesInCache(it) }
            .catch { emit(getCachedMoviesByType(ProgramType.Movie.TopRated)) }

    private suspend fun saveMoviesInCache(programs: List<Program>) {
        programs.firstOrNull()?.let {
            programLocalDataSource.deleteProgramsByType(it.type)
        }
        programLocalDataSource.savePrograms(programs)
    }

    private suspend fun getCachedMoviesByType(type: ProgramType): List<Program> =
        programLocalDataSource.getProgramsByType(type)
}
