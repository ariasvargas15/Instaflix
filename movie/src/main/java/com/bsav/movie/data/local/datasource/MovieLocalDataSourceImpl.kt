package com.bsav.movie.data.local.datasource

import com.bsav.core.db.dao.MovieDao
import com.bsav.movie.data.local.mapFromDomainToEntity
import com.bsav.movie.data.local.mapFromEntityToDomain
import com.bsav.movie.domain.model.Movie
import javax.inject.Inject

class MovieLocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao
) : MovieLocalDataSource {

    override suspend fun getMovieById(id: Int): Movie {
        return movieDao.getMovieById(id).mapFromEntityToDomain()
    }

    override suspend fun saveMovie(movie: Movie) {
        movieDao.saveMovie(movie.mapFromDomainToEntity())
    }
}
