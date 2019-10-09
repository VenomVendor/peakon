/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.app

import androidx.annotation.LayoutRes
import androidx.navigation.fragment.NavHostFragment
import com.venomvendor.peakon.R
import com.venomvendor.peakon.app.common.BaseActivity

class MainActivity : BaseActivity() {
    @LayoutRes
    // Return current layout
    override val layout = R.layout.activity_main

    override fun initViews() {
        // Get Navigation Graph
        val host = NavHostFragment.create(R.navigation.nav_graph)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame, host)
            .setPrimaryNavigationFragment(host)
            .commit()
    }
}
