package com.sparkfusion.dataport.admin.portstudents.entity

import com.sparkfusion.data.commonentity.institution.CommonFacultyDataEntity

data class FacultyEntity(
    override val id: Int,
    override val name: String,
    override val abbreviation: String
) : CommonFacultyDataEntity
