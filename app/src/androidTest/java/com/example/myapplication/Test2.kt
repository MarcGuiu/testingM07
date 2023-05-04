package com.example.myapplication

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.PositionAssertions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.rule.ActivityTestRule



@RunWith(AndroidJUnit4::class)

class Test2{
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun checkElementPosition() {
        onView(withId(R.id.imageView3))
            .check(PositionAssertions.isCompletelyAbove(withId(R.id.username)))
        onView(withId(R.id.username))
            .check(PositionAssertions.isCompletelyAbove(withId(R.id.password)))
        onView(withId(R.id.button))
            .check(PositionAssertions.isCompletelyBelow(withId(R.id.password)))
    }
}