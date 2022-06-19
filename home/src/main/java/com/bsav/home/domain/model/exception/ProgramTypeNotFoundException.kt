package com.bsav.home.domain.model.exception

class ProgramTypeNotFoundException(
    override val message: String = "Program type not found"
) : Exception()
