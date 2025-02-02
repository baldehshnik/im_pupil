package com.sparkfusion.portdomainservices.admin.portstatistics.model

data class ReadGroupMemberModel(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val prefect: Boolean,
    val code: String,
    val pupil: PupilModel?
)
