package com.sparkfusion.data.pupil.entity.students

data class GroupMemberDataEntity(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val prefect: Boolean,
    val code: String,
    val pupil: PupilDataEntity?,
    val educationPlaceDto: EducationPlaceDataEntity?
)
