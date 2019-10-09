/*
 * Copyright (c) 2019 VenomVendor. All rights reserved.
 * Created by VenomVendor on 09-Oct-2019.
 */

package com.venomvendor.peakon.employee

import com.openpojo.reflection.impl.PojoClassFactory
import com.openpojo.validation.ValidatorBuilder
import com.openpojo.validation.rule.impl.GetterMustExistRule
import com.openpojo.validation.rule.impl.NoNestedClassRule
import com.openpojo.validation.rule.impl.SetterMustExistRule
import com.openpojo.validation.test.impl.DefaultValuesNullTester
import com.openpojo.validation.test.impl.GetterTester
import com.openpojo.validation.test.impl.SetterTester
import com.venomvendor.peakon.employee.model.Employee
import org.junit.Test

/**
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class EmployeeDetailPojoTest {

    @Test
    fun searchTeamPojoTest() {
        val validator = ValidatorBuilder.create()
            .with(GetterMustExistRule())
            .with(SetterMustExistRule())
            .with(SetterTester())
            .with(GetterTester())
            .with(DefaultValuesNullTester())
            .with(NoNestedClassRule())
            // Build assertion criteria
            .build()

        // Add all classes
        val clzez = setOf(
            Employee::class.java
        )

        for (clazz in clzez) {
            // This is where the assertion happens
            validator.validate(PojoClassFactory.getPojoClass(clazz))
        }
    }
}
