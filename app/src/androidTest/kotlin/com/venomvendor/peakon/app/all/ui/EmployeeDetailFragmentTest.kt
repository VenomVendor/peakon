/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.app.all.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.venomvendor.peakon.R
import com.venomvendor.peakon.helper.BaseTest
import com.venomvendor.peakon.helper.RecyclerViewItemCountAssertion.withItemCount
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.Before
import org.junit.Test
import org.koin.core.get

class EmployeeDetailFragmentTest : BaseTest() {

    @Before
    override fun setUp() {
        super.setUp()

        val response = with(this).read("response.json")
        val server = get<MockWebServer>()
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(response)
        )

        /* Launches Fragment directly */
        launchFragmentInContainer<EmployeeListFragment>(null, R.style.AppTheme)
    }

    @Test
    fun getDataSuccessfullyWithResults() {
        val query = "U"

        // Start Typing
        onView(withId(R.id.search)).perform(
            typeText(query),
            closeSoftKeyboard()
        )

        onView(withId(R.id.team_list))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(Int.MAX_VALUE - 1))

        onView(withId(R.id.team_list))
            .check(withItemCount(4))
    }

    @Test
    fun getDataSuccessfullyWithZeroResults() {
        val query = "UB"

        // Start Typing
        onView(withId(R.id.search)).perform(
            typeText(query),
            closeSoftKeyboard()
        )

        onView(withId(R.id.team_list))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(Int.MAX_VALUE - 1))

        onView(withId(R.id.team_list))
            .check(withItemCount(0))
    }

    @Test
    fun getDataSuccessfullyWithNoResults() {
        var query = "A"

        // Start Typing
        onView(withId(R.id.search)).perform(
            typeText(query),
            closeSoftKeyboard()
        )

        query = ""

        // Start Typing
        onView(withId(R.id.search)).perform(
            replaceText(query),
            closeSoftKeyboard()
        )

        onView(withId(R.id.team_list))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(Int.MAX_VALUE - 1))

        onView(withId(R.id.team_list))
            .check(withItemCount(0))
    }
}
