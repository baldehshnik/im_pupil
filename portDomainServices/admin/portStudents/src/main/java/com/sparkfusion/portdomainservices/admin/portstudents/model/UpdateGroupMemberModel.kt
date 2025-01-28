package com.sparkfusion.portdomainservices.admin.portstudents.model

data class UpdateGroupMemberModel(
    val id: Int?,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val code: String
)
