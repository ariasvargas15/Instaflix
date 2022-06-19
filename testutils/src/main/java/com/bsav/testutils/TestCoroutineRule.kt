package com.bsav.testutils

import com.bsav.core.utils.CoroutineContextProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement


@ExperimentalCoroutinesApi
class TestCoroutineRule : TestRule {
    private val job = SupervisorJob()
    val coroutineContextProvider = object : CoroutineContextProvider {
        override val io: CoroutineDispatcher
            get() = Dispatchers.Unconfined
    }

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            override fun evaluate() {
                Dispatchers.setMain(Dispatchers.Unconfined)
                base.evaluate()
                job.cancel()
                Dispatchers.resetMain()
            }
        }
    }

}
