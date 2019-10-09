/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

@file:Suppress("unused")

package com.venomvendor.peakon.app.core

import android.annotation.SuppressLint
import com.facebook.stetho.Stetho
import com.venomvendor.peakon.core.di.coreDebugModule
import com.venomvendor.peakon.debug.di.networkTrackerModule
import org.koin.core.context.loadKoinModules

/**
 * Application class for core functionality.
 */
@SuppressLint("Registered")
class DebugCustomApplication : CustomApplication() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        loadKoinModules(
            listOf(
                networkTrackerModule, coreDebugModule
            )
        )
    }
}
