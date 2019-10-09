/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.employee.repository

import androidx.annotation.CheckResult
import com.venomvendor.peakon.core.data.RepositoryManager
import com.venomvendor.peakon.employee.db.EmployeeDatabase
import com.venomvendor.peakon.employee.factory.EmployeeService
import com.venomvendor.peakon.employee.model.Employee
import io.reactivex.Single
import org.koin.core.KoinComponent

class EmployeeRepository(
    apiService: EmployeeService,
    storage: EmployeeDatabase
) : KoinComponent {
    private var repo = AllEmployeeRepository(storage.teamDao(), apiService)
    private val mEmployee: RepositoryManager<List<Employee>> by lazy {
        // Create request object
        RepositoryManager.create(repo)
    }

    @CheckResult
    fun getTeams(query: String): Single<List<Employee>> {
        repo.apply {
            this.search = query
        }
        // Execute repository
        return mEmployee.execute()
    }
}
