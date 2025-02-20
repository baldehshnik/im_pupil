package com.sparkfusion.dataport.pupil.portmagazine

data class GroupMemberEntity(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val prefect: Boolean,
    val code: String,
    val pupil: PupilEntity?,
    val educationPlaceDto: EducationPlaceEntity?
)
