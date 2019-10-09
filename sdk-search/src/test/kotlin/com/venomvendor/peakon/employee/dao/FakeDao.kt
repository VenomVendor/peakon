/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.employee.dao

import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.venomvendor.peakon.employee.db.EmployeeDatabase
import org.mockito.Mockito

class FakeDao(private val employeeDao: EmployeeDao?) :
    EmployeeDatabase() {
    override fun teamDao(): EmployeeDao {
        return employeeDao!!
    }

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        return Mockito.mock(SupportSQLiteOpenHelper::class.java)
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        return Mockito.mock(InvalidationTracker::class.java)
    }

    override fun clearAllTables() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
