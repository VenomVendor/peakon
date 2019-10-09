/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.employee.viewmodel

import androidx.annotation.CheckResult
import androidx.lifecycle.ViewModel
import com.venomvendor.peakon.employee.model.Employee
import com.venomvendor.peakon.employee.repository.EmployeeRepository
import io.reactivex.Single
import org.koin.core.KoinComponent

class EmployeeViewModel(
    private val employeeRepo: EmployeeRepository
) : ViewModel(), KoinComponent {
    /**
     * THIS API WRAPS **DATA**
     * ******************************************
     * Inside [Single]. Error is propagated as is.
     * ******************************************
     * Get list of EmployeeDetail
     *
     * @return List of [Employee] wrapped inside response.
     */
    @CheckResult
    fun getTeams(search: String): Single<List<Employee>> {
        return employeeRepo.getTeams(search)
    }
}
