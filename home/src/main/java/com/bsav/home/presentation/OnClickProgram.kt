package com.bsav.home.presentation

import com.bsav.home.domain.model.ProgramType

interface OnClickProgram {
    fun goToDetails(programId: Int, programType: ProgramType)
}
