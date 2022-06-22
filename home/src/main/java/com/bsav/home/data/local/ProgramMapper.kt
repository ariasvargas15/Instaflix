package com.bsav.home.data.local

import com.bsav.db.entity.ProgramEntity
import com.bsav.home.domain.model.Program
import com.bsav.home.domain.model.ProgramType

fun ProgramEntity.mapFromEntityToDomain(): Program {
    return Program(id, name, posterPath, type.mapToProgramType())
}

fun ProgramType.mapToString(): String {
    return this::class.java.name
}

fun Program.mapFromDomainToEntity(): ProgramEntity {
    return ProgramEntity(id, name, posterPath ?: "", type.mapToString())
}

fun String.mapToProgramType(): ProgramType {
    return Class.forName(this).kotlin.objectInstance as ProgramType
}
