package com.sparkfusion.features.admin.services.utils

import com.sparkfusion.core.resource.R as CoreResource

fun getServiceImageId(resourceName: String): Int {
    val idField = CoreResource.drawable::class.java.getDeclaredField(resourceName)
    return idField.getInt(idField)
}