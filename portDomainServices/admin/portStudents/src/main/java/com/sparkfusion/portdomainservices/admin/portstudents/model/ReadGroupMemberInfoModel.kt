package com.sparkfusion.portdomainservices.admin.portstudents.model

data class ReadGroupMemberInfoModel(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val prefect: Boolean,
    val code: String
)
