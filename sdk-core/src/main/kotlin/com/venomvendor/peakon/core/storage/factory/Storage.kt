/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.core.storage.factory

/**
 * Template for storage operation
 */
interface Storage {

    /**
     * Save data
     */
    fun <E> save(data: E)

    /**
     * Update data
     */
    fun <E> update(data: E)

    /**
     * Delete data
     */
    fun <E> delete(data: E)

    /**
     * Retrieve data from storage
     */
    fun <T> retrieve(clz: Class<T>): List<T>?
}
