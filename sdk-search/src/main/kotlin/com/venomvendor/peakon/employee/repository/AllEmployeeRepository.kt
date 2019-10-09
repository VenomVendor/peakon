/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.employee.repository

import android.annotation.SuppressLint
import com.venomvendor.peakon.core.data.factory.Repository
import com.venomvendor.peakon.employee.dao.EmployeeDao
import com.venomvendor.peakon.employee.factory.EmployeeService
import com.venomvendor.peakon.employee.model.Employee
import io.reactivex.Single
import org.koin.core.KoinComponent

class AllEmployeeRepository(
    private val storage: EmployeeDao,
    private val apiService: EmployeeService
) : Repository<List<Employee>>, KoinComponent {

    lateinit var search: String

    override fun cachedData(): List<Employee>? {
        return cachedData(true)
    }

    @SuppressLint("CheckResult")
    private fun cachedData(retry: Boolean): List<Employee>? {
        storage.getAll(search)
            .run {
                if (isEmpty() && storage.getCount() == 0) {
                    /****** THIS IS A HACK :: START
                     * RIGHT WAY IS TO HAVE SINGLE SOURCE OF TRUTH
                     * such as Database & subscribe to changes ******/
                    if (retry && request().blockingGet() != null) {
                        return cachedData(false)
                    }
                    /****** THIS IS A HACK :: END ******/
                    return null
                }
                return this
            }
    }

    override fun request(): Single<List<Employee>> {
        return apiService.getEmployee()
            .map {
                it.employees
            }
            .doOnSuccess {
                if (it.isEmpty()) {
                    // TODO: Wrap in nice exception
                    throw RuntimeException("Unknown Error Occurred")
                }
            }
            .doOnSuccess {
                saveData(it)
            }
    }

    @Throws(Exception::class)
    override fun saveData(data: List<Employee>) {
        // Do In parallel
        storage.insert(data)
//            Uncomment below, once Hacky way is fixed.
//            .subscribeOn(Schedulers.io())
            .subscribe()
    }
}
