package com.bsav.home.domain.usecase.navigator

import androidx.core.net.toUri
import com.bsav.home.domain.model.Destination
import javax.inject.Inject

class GetDeepLink @Inject constructor() {
    operator fun invoke(params: Map<String, Any>, path: String): Destination {
        var deepLink = "$DEEP_LINK_BASE$path"
        if (params.isNotEmpty()) {
            deepLink += "?"
        }
        params.onEachIndexed { index, entry ->
            deepLink += "${entry.key}=${entry.value}"
            if (index < params.size - 1) {
                deepLink += ","
            }
        }
        return Destination(deepLink.toUri())
    }
}

private const val DEEP_LINK_BASE = "myapp://com.bsav.instaflix/"
