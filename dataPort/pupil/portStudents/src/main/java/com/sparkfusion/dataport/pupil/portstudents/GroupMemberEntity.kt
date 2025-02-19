package com.sparkfusion.dataport.pupil.portstudents

data class GroupMemberEntity(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val isPrefect: Boolean,
    val code: String,
    val pupil: PupilEntity?,
    val educationPlaceDto: EducationPlaceEntity?
)
