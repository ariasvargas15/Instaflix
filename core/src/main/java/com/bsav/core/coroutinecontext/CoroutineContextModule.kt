package com.bsav.core.coroutinecontext

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
object CoroutineContextModule {

    @Provides
    @Singleton
    fun provideCoroutineContextProvider(): CoroutineContextProvider =
        object : CoroutineContextProvider {
            override val io: CoroutineContext
                get() = Dispatchers.IO
            override val computation: CoroutineContext
                get() = Dispatchers.Unconfined
        }
}
