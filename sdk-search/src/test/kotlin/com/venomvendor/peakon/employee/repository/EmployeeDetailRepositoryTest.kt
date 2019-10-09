/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.employee.repository

import com.google.gson.Gson
import com.venomvendor.peakon.core.di.coreModule
import com.venomvendor.peakon.employee.dao.EmployeeDao
import com.venomvendor.peakon.employee.dao.FakeDao
import com.venomvendor.peakon.employee.db.EmployeeDatabase
import com.venomvendor.peakon.employee.di.employeeModule
import com.venomvendor.peakon.employee.factory.EmployeeService
import com.venomvendor.peakon.employee.helper.BaseTest
import com.venomvendor.peakon.employee.model.EmployeeWrapper
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.test.get
import org.mockito.Mockito
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.`when` as mockitoWhen

class EmployeeDetailRepositoryTest : BaseTest() {

    override fun before() {
        super.before()
        startKoin {
            modules(listOf(coreModule, employeeModule))
        }
    }

    @Test
    fun `teamTeams With Results`() {
        val response: String = with(this).read("response.json")
        val teamObj = get<Gson>().fromJson(
            response,
            EmployeeWrapper::class.java
        )

        val employee: EmployeeService = Mockito.mock(EmployeeService::class.java)
        val teamDao = Mockito.mock(EmployeeDao::class.java)

        val fakeDao: EmployeeDatabase = FakeDao(teamDao)
        val teamRepository = EmployeeRepository(employee, fakeDao)

        mockitoWhen(teamDao.getAll())
            .thenReturn(listOf())

        doReturn(Completable.complete())
            .`when`(teamDao).insert(teamObj.employees)

        mockitoWhen(employee.getEmployee())
            .thenReturn(Single.just(teamObj))

        teamRepository.getTeams("ABC")
            .test()
            .assertSubscribed()
            .assertComplete()
            .assertNoErrors()
            .assertValue {
                it.count() == teamObj.employees.count()
            }
    }
}
