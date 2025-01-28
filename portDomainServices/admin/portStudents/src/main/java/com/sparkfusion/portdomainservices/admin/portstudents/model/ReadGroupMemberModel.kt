package com.sparkfusion.portdomainservices.admin.portstudents.model

data class ReadGroupMemberModel(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val isPrefect: Boolean,
    val code: String,
    val pupil: PupilModel?,
    val educationPlaceDto: EducationPlaceModel?
)
