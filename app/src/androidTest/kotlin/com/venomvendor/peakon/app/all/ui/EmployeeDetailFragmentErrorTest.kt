/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.app.all.ui

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.venomvendor.peakon.R
import com.venomvendor.peakon.employee.db.EmployeeDatabase
import com.venomvendor.peakon.helper.BaseTest
import com.venomvendor.peakon.helper.RecyclerViewItemCountAssertion.withItemCount
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.get
import org.koin.core.inject
import java.io.IOException

class EmployeeDetailFragmentErrorTest : BaseTest() {
    private val db by inject<EmployeeDatabase>()

    @Before
    override fun setUp() {
        super.setUp()

        val response = with(this).read("error.json")
        val server = get<MockWebServer>()
        server.enqueue(
            MockResponse()
                .setResponseCode(200)
                .setBody(response)
        )

        db.teamDao().deleteAll()

        /* Launches Fragment directly */
        launchFragmentInContainer<EmployeeListFragment>(null, R.style.AppTheme)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun getNoResultsButError() {
        val query = "U"

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
}
