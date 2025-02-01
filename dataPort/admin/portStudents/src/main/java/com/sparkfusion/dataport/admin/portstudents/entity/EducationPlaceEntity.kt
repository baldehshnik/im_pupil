package com.sparkfusion.dataport.admin.portstudents.entity

import com.sparkfusion.data.commonentity.CommonEducationPlaceDataEntity

data class EducationPlaceEntity(
    override val institutionName: String,
    override val facultyName: String,
    override val groupName: String
): CommonEducationPlaceDataEntity
