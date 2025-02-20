package com.sparkfusion.dataport.pupil.portstatistics.entity

data class GroupMemberEntity(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val isPrefect: Boolean,
    val code: String,
    val pupil: PupilEntity?
)
