/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.app.util

import androidx.recyclerview.widget.DiffUtil
import com.venomvendor.peakon.employee.model.Employee

/**
 * Helper Utility for Recycler View's Data.
 */
class DiffUtilHelper private constructor() {

    init {
        throw UnsupportedOperationException("Instance should not be created")
    }

    companion object {

        val EMPLOYEE_DIFF: DiffUtil.ItemCallback<Employee> =
            object : DiffUtil.ItemCallback<Employee>() {
                override fun areItemsTheSame(
                    oldItem: Employee,
                    newItem: Employee
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: Employee,
                    newItem: Employee
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}
