/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.core.di

import com.google.gson.Gson
import com.venomvendor.peakon.core.helper.BaseTest
import com.venomvendor.peakon.core.helper.di.BASE_URL
import com.venomvendor.peakon.core.helper.di.testModule
import com.venomvendor.peakon.core.helper.model.TestData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.inject
import retrofit2.Retrofit

class CoreModuleTest : BaseTest() {

    override fun before() {
        super.before()
        startKoin {
            modules(listOf(coreModule, testModule))
        }
    }

    @Test
    fun testBaseUrl() {
        val retrofit by inject<Retrofit>()
        assertEquals(BASE_URL, retrofit.baseUrl().toString())
    }

    @Test
    fun `testGsonTypeAdapter with result(+ve)`() {
        val gson by inject<Gson>()

        val testData: TestData =
            gson.fromJson<TestData>("{\"id\":1,\"name\":\"test\"}", TestData::class.java)
        assertEquals("test", testData.name)
    }

    @Test
    fun `testGsonTypeAdapter with Missing Required Data(-ve)`() {
        val gson by inject<Gson>()
        val testData: TestData? =
            gson.fromJson<TestData>("{\"id\":1,\"name\":null}", TestData::class.java)
        assertNull(testData)
    }
}
