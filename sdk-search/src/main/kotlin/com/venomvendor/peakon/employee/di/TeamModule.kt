/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.employee.di

import com.venomvendor.peakon.core.network.NetworkManager
import com.venomvendor.peakon.core.storage.SharedPreferencesManager
import com.venomvendor.peakon.core.storage.factory.Storage
import com.venomvendor.peakon.employee.db.EmployeeDatabase
import com.venomvendor.peakon.employee.factory.EmployeeService
import com.venomvendor.peakon.employee.repository.EmployeeRepository
import com.venomvendor.peakon.employee.viewmodel.EmployeeViewModel
import org.koin.dsl.module

val employeeModule = module {

    factory {
        EmployeeViewModel(get())
    }

    factory {
        EmployeeRepository(get(), get())
    }

    factory {
        SharedPreferencesManager() as Storage
    }

    factory {
        get<NetworkManager>().getWebService<EmployeeService>(get())
    }

    single {
        EmployeeDatabase.getInstance(get())
    }
}
