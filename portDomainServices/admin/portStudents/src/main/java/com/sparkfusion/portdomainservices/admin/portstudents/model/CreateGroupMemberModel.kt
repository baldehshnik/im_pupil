package com.sparkfusion.portdomainservices.admin.portstudents.model

data class CreateGroupMemberModel(
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val code: String
)