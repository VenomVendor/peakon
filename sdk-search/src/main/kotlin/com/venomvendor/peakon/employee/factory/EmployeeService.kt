/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.employee.factory

import com.venomvendor.peakon.employee.model.EmployeeWrapper
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Webservice to make employee API calls
 */
interface EmployeeService {

    /**
     * Get all Teams
     * TODO: Change response object type &amp;
     * use custom [GSON] adapter to handle error object, response array in single Type
     *
     * @return SingleObservable to subscribe the result.
     */
    @GET("/daviferreira/41238222ac31fe36348544ee1d4a9a5e/raw/5dc996407f6c9a6630bfcec56eee22d4bc54b518/employees.json")
    fun getEmployee(): Single<EmployeeWrapper>
}
