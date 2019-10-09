/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.employee.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.venomvendor.peakon.employee.dao.EmployeeDao
import com.venomvendor.peakon.employee.model.Employee

/**
 * The Room database that contains the Users table
 */
@Database(entities = [Employee::class], version = 1)
abstract class EmployeeDatabase : RoomDatabase() {

    abstract fun teamDao(): EmployeeDao

    companion object {

        @Volatile
        private var INSTANCE: EmployeeDatabase? = null

        fun getInstance(context: Context): EmployeeDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                EmployeeDatabase::class.java, "team.db"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}
