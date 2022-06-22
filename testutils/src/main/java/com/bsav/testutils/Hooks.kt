package com.bsav.testutils

import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.AmbiguousViewMatcherException
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSubstring
import org.junit.Assert

fun isTextDisplayed(text: String) {
    var isDisplayed = true
    onView(withSubstring(text))
        .withFailureHandler { error, _ ->
            isDisplayed = error is AmbiguousViewMatcherException
        }.check(matches(isDisplayed()))
    Assert.assertTrue(isDisplayed)
}

fun isTextNotDisplayed(text: String) {
    var isDisplayed = true
    onView(withSubstring(text))
        .withFailureHandler { error, _ ->
            isDisplayed = error is AmbiguousViewMatcherException
        }
        .check(matches(isDisplayed()))
    Assert.assertFalse("Text: $text is displayed", isDisplayed)
}

fun scrollUp() {
    Espresso.closeSoftKeyboard()
    onView(withId(android.R.id.content))
        .perform(ViewActions.swipeUp())
}

fun scrollListToPosition(@IdRes recyclerViewId: Int, position: Int) {
    onView(withId(recyclerViewId))
        .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, ViewActions.scrollTo()))
}

fun verifyItemsCountInRecyclerView(@IdRes recyclerViewId: Int, expectedItemsCount: Int) {
    onView(withId(recyclerViewId)).check { view, noViewFoundException ->
        if (view !is RecyclerView) throw noViewFoundException
        Assert.assertEquals(expectedItemsCount, view.adapter?.itemCount)
    }
}

fun isViewDisplayed(id: Int) {
    onView(withId(id))
        .check(matches(isDisplayed()))
}

fun clickOnListWithPosition(@IdRes recyclerViewId: Int, position: Int) {
    onView(withId(recyclerViewId))
        .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(position, ViewActions.click()))
}
