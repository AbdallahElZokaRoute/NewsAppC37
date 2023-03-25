package com.route.newsappc37.ui.fragments.news

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import com.route.newsappc37.R
import org.junit.Test

class NewsFragmentTest {

    // UI Testing ->
    /*
            You want to test ui using Espresso UI

     Espresso UI Testing :-
            is A Library that communicates with emulator or device and simplifies actions and do it fast

     */
    @Test
    fun testTabBar_by_clickingSecondTab_then_verifyItemsOfRecyclerItsSameIdOfTab() {

        Espresso.onView(ViewMatchers.withId(R.id.news_sources_tab_layout)).perform()

    }

}