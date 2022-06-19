package com.bsav.home.domain.usecase.navigator

import com.bsav.home.domain.model.Destination
import com.bsav.home.domain.model.ProgramType
import com.bsav.home.domain.model.exception.ProgramTypeNotFoundException
import javax.inject.Inject

class ProgramNavigator @Inject constructor(
    private val routes: Map<@JvmSuppressWildcards ProgramType, String>,
    private val getDeepLink: GetDeepLink
) {
    fun resolveDestination(params: Map<String, Any>, programType: ProgramType): Destination {
        return routes[programType]?.let { getDeepLink(params, it) } ?: throw ProgramTypeNotFoundException()
    }
}
