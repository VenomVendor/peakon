/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.employee.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName
import com.venomvendor.peakon.core.annotation.Mandatory
import com.venomvendor.peakon.employee.db.AttributeConverter
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Employee(
    @Embedded
    @field:TypeConverters(AttributeConverter::class)
    @field:SerializedName("attributes")
    val attributes: Attributes,

    @ColumnInfo(name = "eid")
    @field:Mandatory
    @field:SerializedName("id")
    @PrimaryKey
    val id: String
) : Parcelable
