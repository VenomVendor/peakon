/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.app.core

import android.app.Application
import com.venomvendor.peakon.core.di.coreModule
import com.venomvendor.peakon.employee.di.employeeModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Application class for core functionality.
 */
open class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        // start Koin
        startKoin {
            // Declare used Android context
            androidContext(this@CustomApplication)
            // Declare modules
            modules(
                listOf(
                    coreModule, employeeModule
                )
            )
        }
    }
}
