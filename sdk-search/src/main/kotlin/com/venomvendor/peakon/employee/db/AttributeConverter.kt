/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.employee.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.venomvendor.peakon.employee.model.Attributes

class AttributeConverter {

    @TypeConverter
    fun fromString(value: String): Attributes {
        return Gson().fromJson(value, Attributes::class.java)
    }

    @TypeConverter
    fun fromList(list: Attributes): String {
        return Gson().toJson(list)
    }
}
