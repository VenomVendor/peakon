/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.employee.viewmodel

import com.google.gson.Gson
import com.venomvendor.peakon.core.di.coreModule
import com.venomvendor.peakon.employee.di.employeeModule
import com.venomvendor.peakon.employee.helper.BaseTest
import com.venomvendor.peakon.employee.model.EmployeeWrapper
import com.venomvendor.peakon.employee.repository.EmployeeRepository
import io.reactivex.Single
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.get
import org.mockito.Mockito
import org.mockito.Mockito.anyString
import org.mockito.Mockito.`when` as mockitoWhen

class EmployeeDetailViewModelTest : BaseTest() {

    override fun before() {
        super.before()
        startKoin {
            modules(listOf(coreModule, employeeModule))
        }
    }

    @Test
    fun `getTeams With Results`() {
        val response: String = with(this).read("response.json")
        val teamObj = get<Gson>().fromJson(
            response,
            EmployeeWrapper::class.java
        )

        val mockRepository = Mockito.mock(EmployeeRepository::class.java)

        mockitoWhen(mockRepository.getTeams(anyString()))
            .thenReturn(
                Single.just(teamObj.employees)
            )

        val randomUserViewModel = EmployeeViewModel(mockRepository)

        randomUserViewModel.getTeams("ABC")
            .test()
            .assertSubscribed()
            .assertComplete()
            .assertNoErrors()
            .assertValueCount(1)
            .assertValue {
                it.count() == teamObj.employees.count()
            }
    }

    @Test
    fun `getTeams With Error`() {
        val mockRepository = Mockito.mock(EmployeeRepository::class.java)

        mockitoWhen(mockRepository.getTeams(anyString()))
            .thenReturn(
                Single.error(Exception("Test"))
            )

        val randomUserViewModel = EmployeeViewModel(mockRepository)

        randomUserViewModel.getTeams("ERR")
            .test()
            .assertSubscribed()
            .assertError {
                it.message == "Test"
            }
    }
}
