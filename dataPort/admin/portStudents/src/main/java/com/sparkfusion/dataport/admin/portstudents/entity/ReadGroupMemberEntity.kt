package com.sparkfusion.dataport.admin.portstudents.entity

import com.sparkfusion.data.commonentity.CommonReadGroupMemberDataEntity

data class ReadGroupMemberEntity(
    override val id: Int,
    override val firstname: String,
    override val lastname: String,
    override val patronymic: String,
    override val prefect: Boolean,
    override val code: String,
    override val pupil: PupilEntity?,
    override val educationPlaceDto: EducationPlaceEntity?
): CommonReadGroupMemberDataEntity
