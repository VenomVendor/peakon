/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.app.common.factory

import android.view.View

/**
 * Handle item click
 *
 * @param <T> Type of data
</T> */
interface OnItemClickListener<T> {

    /**
     * Callback when item is clicked
     */
    fun onClick(item: T, view: View, position: Int)
}
