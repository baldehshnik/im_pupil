package com.sparkfusion.portdomainservices.pupil.portstatistics.model

data class GroupMemberInfoModel(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val isPrefect: Boolean,
    val code: String
)
