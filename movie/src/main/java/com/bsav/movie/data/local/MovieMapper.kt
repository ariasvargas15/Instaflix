package com.bsav.movie.data.local

import com.bsav.db.entity.MovieEntity
import com.bsav.movie.domain.model.Movie

fun MovieEntity.mapFromEntityToDomain(): Movie {
    return Movie(
        id,
        title,
        posterPath,
        adult,
        backdropPath,
        overview,
        releaseDate,
        voteAverage,
    )
}

fun Movie.mapFromDomainToEntity(): MovieEntity {
    return MovieEntity(
        id,
        title,
        posterPath,
        adult,
        backdropPath ?: "",
        overview,
        releaseDate,
        voteAverage,
    )
}
