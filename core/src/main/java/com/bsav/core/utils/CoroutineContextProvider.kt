package com.bsav.core.utils

import kotlinx.coroutines.CoroutineDispatcher

interface CoroutineContextProvider {
    val io: CoroutineDispatcher
}