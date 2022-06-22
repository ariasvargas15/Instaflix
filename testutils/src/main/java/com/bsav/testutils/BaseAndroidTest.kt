package com.bsav.testutils

import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.Before
import org.junit.Rule

abstract class BaseAndroidTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    open fun setup() {
        hiltRule.inject()
    }
}
