package com.bsav.tvshow.data.local

import com.bsav.db.entity.TvShowEntity
import com.bsav.tvshow.domain.model.TvShow

fun TvShowEntity.mapFromEntityToDomain(): TvShow {
    return TvShow(
        id,
        name,
        posterPath,
        adult,
        backdropPath,
        overview,
        releaseDate,
        voteAverage,
        seasons,
        episodes
    )
}

fun TvShow.mapFromDomainToEntity(): TvShowEntity {
    return TvShowEntity(
        id,
        name,
        posterPath,
        adult,
        backdropPath ?: "",
        overview,
        releaseDate,
        voteAverage,
        seasons,
        episodes
    )
}
