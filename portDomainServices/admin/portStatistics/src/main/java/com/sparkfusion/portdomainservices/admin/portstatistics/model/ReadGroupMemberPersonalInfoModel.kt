package com.sparkfusion.portdomainservices.admin.portstatistics.model

data class ReadGroupMemberPersonalInfoModel(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val isPrefect: Boolean,
    val code: String
)
