package com.bsav.core.coroutinecontext

import kotlin.coroutines.CoroutineContext

interface CoroutineContextProvider {
    val io: CoroutineContext
    val computation: CoroutineContext
}
