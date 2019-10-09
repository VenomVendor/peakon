/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.employee.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.venomvendor.peakon.core.annotation.Mandatory
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class Attributes(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long,

    @field:Mandatory
    @field:SerializedName("firstName")
    var firstName: String,

    @field:Mandatory
    @field:SerializedName("lastName")
    var lastName: String,

    @field:Mandatory
    @field:SerializedName("name")
    var name: String,

    @field:SerializedName("Job Level")
    var jobLevel: String? = null,

    @field:SerializedName("Gender")
    var gender: String? = null,

    @field:SerializedName("employmentStart")
    var employmentStart: String? = null
) : Parcelable
