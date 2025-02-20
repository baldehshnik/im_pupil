package com.sparkfusion.portdomainservices.pupil.portmagazine.model

data class GroupMemberModel(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val prefect: Boolean,
    val code: String,
    val pupil: PupilModel?,
    val educationPlaceDto: EducationPlaceModel?
)
