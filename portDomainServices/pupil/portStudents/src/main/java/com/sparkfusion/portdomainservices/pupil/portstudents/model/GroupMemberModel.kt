package com.sparkfusion.portdomainservices.pupil.portstudents.model

data class GroupMemberModel(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val isPrefect: Boolean,
    val code: String,
    val pupil: PupilModel?,
    val educationPlaceDto: EducationPlaceModel?
)
