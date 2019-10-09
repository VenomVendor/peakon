/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.employee.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.venomvendor.peakon.employee.model.Employee
import io.reactivex.Completable

@Dao
interface EmployeeDao {
    @Query("SELECT * FROM employee")
    fun getAll(): List<Employee>

    @Query("SELECT COUNT(*) FROM employee")
    fun getCount(): Int

    //    @Query("SELECT * FROM employee WHERE name LIKE  '%' || :query || '%' ORDER BY firstName ASC")
    @Query("SELECT * FROM employee WHERE firstName LIKE  '%' || :query || '%' OR lastName LIKE  '%' || :query || '%' ORDER BY firstName ASC")
    fun getAll(query: String): List<Employee>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg employees: Employee): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(employees: List<Employee>): Completable

    @Delete
    fun delete(employee: Employee): Completable

    @Query("DELETE FROM employee")
    fun deleteAll()
}
