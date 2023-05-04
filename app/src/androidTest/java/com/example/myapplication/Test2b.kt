package com.example.myapplication

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.PositionAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.rule.ActivityTestRule
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class Test2b{
    @get:Rule
    val activityRule = ActivityTestRule(Pagina2::class.java)

    @Test
    fun checkElementPosition2() {
        Espresso.onView(ViewMatchers.withId(R.id.benvingut))
            .check(PositionAssertions.isCompletelyAbove(ViewMatchers.withId(R.id.imageView4)))
        Espresso.onView(ViewMatchers.withId(R.id.imageView4))
            .check(PositionAssertions.isCompletelyAbove(ViewMatchers.withId(R.id.textView6)))
        Espresso.onView(ViewMatchers.withId(R.id.textView6))
            .check(PositionAssertions.isCompletelyAbove(ViewMatchers.withId(R.id.searchView)))
        Espresso.onView(ViewMatchers.withId(R.id.searchView))
            .check(PositionAssertions.isCompletelyAbove(ViewMatchers.withId(R.id.rv_list)))
    }
}