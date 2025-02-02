package com.sparkfusion.dataport.admin.portstatistics.entity

data class ReadGroupMemberEntity(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val prefect: Boolean,
    val code: String,
    val pupil: PupilEntity?
)
