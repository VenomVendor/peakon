/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.helper

import com.venomvendor.peakon.app.core.CustomApplication
import com.venomvendor.peakon.app.util.EspressoIdlingResource
import com.venomvendor.peakon.helper.di.appTestModule
import org.koin.core.context.loadKoinModules

class CustomTestApplication : CustomApplication() {

    override fun onCreate() {
        super.onCreate()

        EspressoIdlingResource.setDefaultIdlingResource()

        loadKoinModules(appTestModule)
    }
}
